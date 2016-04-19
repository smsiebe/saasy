/*
 * Copyright 2016 geoint.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geoint.saasy.constraint.multitenant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geoint.saasy.authz.Authorization;
import org.geoint.saasy.authz.AuthorizationException;
import org.geoint.saasy.session.SecuritySession;
import org.geoint.saasy.authz.AuthorizationResult;

/**
 * Ensures the current tenancy of the session permits access to the tenant
 * resource.
 *
 * @author steve_siebert
 */
public class TenantAuthorization implements Authorization {

    private static final Logger LOGGER
            = Logger.getLogger(TenantAuthorization.class.getName());
    public static final TenantAuthorization INSTANCE = new TenantAuthorization();

    private TenantAuthorization() {
    }

    @Override
    public AuthorizationResult authorize(ResourceOperationModel context,
            SecuritySession session, Object instance)
            throws AuthorizationException {
        return context.getConstraints().stream()
                .filter((c) -> c instanceof TenantConstraint) //ignore non-tenant constraints
                .map((c) -> (TenantConstraint) c)
                .filter((c) -> c.getResourceClassName().contentEquals(instance.getClass().getName())) //only constraints associated with this resource type
                .map((c) -> {
                    //authorize if resource tenant is the same as the current 
                    //session tenancy
                    try {
                        Method tenantMethod = instance.getClass().getMethod(c.getTenantMethodName());
                        String tenantGuid = tenantMethod.invoke(instance).toString();
                        if (session.getTenancy().getGuid().contentEquals(tenantGuid)) {
                            return Authorization.AuthorizationResult.AUTHORIZED;
                        }
                        return Authorization.AuthorizationResult.DENIED; //session is not the same tenant
                    } catch (NoSuchMethodException ex) {
                        //tenant accessor defined by the constraint correct 
                        //for this resource
                        LOGGER.log(Level.SEVERE, String.format(
                                "Authorization to tenant-protected resource '%s' "
                                + "could not be completed, resource tenant "
                                + "could not be determined from method '%s' "
                                + "on class '%s'", context.toString(),
                                c.getTenantMethodName(),
                                c.getResourceClassName()), ex);
                        return Authorization.AuthorizationResult.DENIED;
                    } catch (InvocationTargetException |
                            IllegalAccessException |
                            IllegalArgumentException |
                            SecurityException ex) {
                        //tenant information could not be accessed
                        LOGGER.log(Level.SEVERE, String.format("Tenant "
                                + "details could not be retrieved method '%s' "
                                + "of resource instance type '%s'.",
                                c.getTenantMethodName(),
                                c.getResourceClassName()
                        ), ex);
                        return Authorization.AuthorizationResult.DENIED;
                    }
                }).reduce((r1, r2) -> r1.merge(r2))
                .orElse(AuthorizationResult.ABSTAIN);

    }

}
