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

/**
 * Determination of resource authorization.
 *
 * @author steve_siebert
 */
public enum AuthorizationResult {
    /**
     * Indicates this authorization grants access to the resource.
     *
     */
    AUTHORIZED,
    /**
     * Indicates this authorization specifically denies access to the resource.
     */
    DENIED,
    /**
     * Indicates that this authorization does not grant or deny access to the
     * resource.
     */
    ABSTAIN;

    /**
     * Merge two results, returning the combined result.
     * 
     * @param other result to merge
     * @return combined result
     */
    public AuthorizationResult merge(AuthorizationResult other) {
        if (this.equals(AuthorizationResult.DENIED)
                || other.equals(AuthorizationResult.DENIED)) {
            return AuthorizationResult.DENIED;
        }
        if (this.equals(AuthorizationResult.AUTHORIZED)
                || this.equals(AuthorizationResult.AUTHORIZED)) {
            return AuthorizationResult.AUTHORIZED;
        }
        return AuthorizationResult.ABSTAIN;
    }

}
