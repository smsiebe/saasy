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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.geoint.saasy.authz.InvalidConstraintDefinitionException;
import org.geoint.saasy.authz.SecurityConstraint;

/**
 * Constraint which limits access to a resource based on the resource instance
 * tenant ownership.
 *
 * @author steve_siebert
 */
public class TenantConstraint implements SecurityConstraint {

    private final String resourceClassName;
    private final String methodName;

    public TenantConstraint(String resourceClassName, String methodName) {
        this.resourceClassName = resourceClassName;
        this.methodName = methodName;
    }

    /**
     * Create a constraint for the provided resource method.
     *
     * @param resourceActionMethod resource method
     * @return
     * @throws InvalidConstraintDefinitionException
     */
    public static TenantConstraint forMethod(Method resourceActionMethod)
            throws InvalidConstraintDefinitionException {

        Class<?> resourceClass = resourceActionMethod.getDeclaringClass();

        Method tenantMethod = tenantAccessorMethod(resourceClass);
        //verify the method takes no parameters
        if (tenantMethod.getParameterCount() > 0) {
            throw new InvalidConstraintDefinitionException("Tenant accessor "
                    + "method must no require parameters.");
        }
        return new TenantConstraint(resourceClass.getName(), tenantMethod.getName());

    }

    /**
     * Find the method of the resource class which is annotated with
     * {@link Tenant}, throwing an exception if there is zero or more than one
     * such methods.
     *
     * @param resourceClass resource class
     * @return method marked with {@link Tenant}
     * @throws InvalidConstraintDefinitionException if no such method exists
     */
    private static Method tenantAccessorMethod(Class<?> resourceClass)
            throws InvalidConstraintDefinitionException {

        List<Method> tenantMethods = Arrays.stream(resourceClass.getMethods())
                .filter((m) -> m.isAnnotationPresent(Tenant.class))
                .collect(Collectors.toList());

        //validate @Tenant is defined properly for the resource
        if (tenantMethods.size() != 1) {
            if (tenantMethods.isEmpty()) {
                throw new InvalidConstraintDefinitionException(
                        String.format("Unable to create tenant constraint, resource class "
                                + "'%s' did not contain a method annotated"
                                + " with @Tenant.", resourceClass.getName()));
            } else {
                throw new InvalidConstraintDefinitionException(
                        String.format("%d methods were defined with "
                                + "@Tenant in resource class '%s', there may be"
                                + " only one.",
                                tenantMethods.size(), resourceClass.getName()));
            }
        }

        return tenantMethods.get(0);
    }

    public String getResourceClassName() {
        return resourceClassName;
    }

    public String getTenantMethodName() {
        return methodName;
    }

    @Override
    public String toString() {
        return String.format("Resource '%s' is constrainted by tenant "
                + "membership defined by the return of method '%s'",
                resourceClassName, methodName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.resourceClassName);
        hash = 53 * hash + Objects.hashCode(this.methodName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TenantConstraint other = (TenantConstraint) obj;
        if (!Objects.equals(this.resourceClassName, other.resourceClassName)) {
            return false;
        }
        if (!Objects.equals(this.methodName, other.methodName)) {
            return false;
        }
        return true;
    }

}
