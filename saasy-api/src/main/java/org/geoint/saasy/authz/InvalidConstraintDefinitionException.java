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
package org.geoint.saasy.authz;

import java.lang.reflect.Method;

/**
 * Thrown if authorization for a resource could not be accomplished because
 * {@link SecurityConstraint constraint} definition was invalid.
 *
 * @author steve_siebert
 */
public class InvalidConstraintDefinitionException extends AuthorizationException {

    public InvalidConstraintDefinitionException(String resourceNamespace,
            String resourceType, String resourceAction) {
        super(String.format("Security constraints for resource '%s.%s#%s'"
                + "are invalid.", resourceNamespace, resourceType,
                resourceAction));
    }

    public InvalidConstraintDefinitionException(Method resourceActionMethod,
            String message) {
        super(String.format("Security constraints for resource action method "
                + "'%s' are invalid: %s", resourceActionMethod.toString(),
                message));
    }

    public InvalidConstraintDefinitionException(String message) {
        super(message);
    }

    public InvalidConstraintDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

}
