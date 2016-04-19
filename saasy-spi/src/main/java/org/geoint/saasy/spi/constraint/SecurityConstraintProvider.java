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
package org.geoint.saasy.spi.constraint;

import java.lang.reflect.Method;
import java.util.Map;
import org.geoint.saasy.IdentityException;
import org.geoint.saasy.UnsupportedSecurityTokenException;
import org.geoint.saasy.authz.Authorization;
import org.geoint.saasy.authz.InvalidConstraintDefinitionException;
import org.geoint.saasy.authz.SecurityConstraint;

/**
 * Lifecyle-managed authorization extension interface used to add support for
 * custom security constraints and authorizations.
 *
 *
 * @param <C> provider constraint type
 * @param <A> provider authorization type
 * @see SecurityConstraint
 * @see Authorization
 * @author steve_siebert
 */
public interface SecurityConstraintProvider<C extends SecurityConstraint, A extends Authorization> {

    /**
     * Initializes the provider.
     *
     * @param properties provider properties
     * @throws IdentityException if the constraint provider cannot be
     * initialized
     */
    void initialize(Map<String, String> properties)
            throws IdentityException;

    /**
     * Create security constraints for a provided method, returning an empty
     * array if no provider-specific constraints are defined for the action.
     *
     * @param resourceActionMethod method representing the resource action
     * @return constraints supported by this provider
     * @throws IllegalStateException if the provider is shutdown
     * @throws InvalidConstraintDefinitionException if the constraint definition
     * for this provider was invalid
     */
    C[] actionConstraints(Method resourceActionMethod)
            throws IllegalStateException, InvalidConstraintDefinitionException;

    /**
     * Create an authorization for a constraint.
     *
     * @param constraint constraint
     * @return authorization
     * @throws UnsupportedSecurityTokenException token is not supported by this
     * provider
     * @throws IllegalStateException if the provider is shutdown
     */
    A createAuthorization(C constraint)
            throws UnsupportedSecurityTokenException, IllegalStateException;

//    String format(C constraint);
//
//    C parseConstraint(String formatted)
//            throws StringParseException;
//
//    String format(A authz);
//
//    A parseAuthorization(String formatted) throws StringParseException;
    /**
     * Synchronously shut down the provider.
     */
    void shutdown();

}
