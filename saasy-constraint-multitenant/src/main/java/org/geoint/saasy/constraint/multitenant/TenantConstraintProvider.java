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

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;
import org.geoint.saasy.UnsupportedSecurityTokenException;
import org.geoint.saasy.authz.InvalidConstraintDefinitionException;
import org.geoint.saasy.spi.constraint.SecurityConstraintProvider;

/**
 * Restricts invocation of resource actions which may only be executed by the
 * tenant/owner of the resource.
 *
 * @author steve_siebert
 */
public class TenantConstraintProvider
        implements SecurityConstraintProvider<TenantConstraint, TenantAuthorization> {

    private static final TenantConstraint[] EMPTY_CONSTRAINTS
            = new TenantConstraint[0];
    private static final Logger LOGGER
            = Logger.getLogger(TenantConstraintProvider.class.getName());

    @Override
    public void initialize(Map<String, String> properties) {
        //no initialization required
    }

    @Override
    public TenantConstraint[] actionConstraints(Method resourceActionMethod)
            throws IllegalStateException, InvalidConstraintDefinitionException {

        if (!resourceActionMethod.isAnnotationPresent(TenantRestricted.class)) {
            LOGGER.finest(() -> String.format("Resource action declared by "
                    + "method '%s' is not tenant restricted.",
                    resourceActionMethod.toString()));
            return EMPTY_CONSTRAINTS;
        }

        return new TenantConstraint[]{TenantConstraint.forMethod(resourceActionMethod)};
    }

    @Override
    public TenantAuthorization createAuthorization(TenantConstraint constraint)
            throws UnsupportedSecurityTokenException, IllegalStateException {
        return TenantAuthorization.INSTANCE;

    }

    @Override
    public void shutdown() {
        //no shutdown required
    }

}
