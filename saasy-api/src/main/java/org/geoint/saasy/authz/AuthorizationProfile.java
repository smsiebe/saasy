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

import java.util.stream.Stream;
import org.geoint.saasy.SecuritySubject;
import org.geoint.saasy.TenantMembership;

/**
 * Subject authorizations associated with a {@link TenantMembership}.
 *
 * @author steve_siebert
 */
public interface AuthorizationProfile {

    /**
     * The subject for which this authorization profile defines access.
     *
     * @return associated subject
     */
    SecuritySubject getSubject();

    /**
     * Return a {@link Stream} that iterates over all the authorizations
     * associated with this profile.
     *
     * @return authorization stream
     */
    Stream<Authorization> stream();

//    /**
//     * Determine if this authorization profile provides the authorizations
//     * necessary to access the specified resource.
//     *
//     * @param resource restricted resource context
//     * @param instance resource instance
//     * @return true if this profile permits access to the resource context,
//     * otherwise false
//     */
//    boolean isAuthorized(OperationModel resource, Object instance);

    /**
     * Grant the provided authorization for this profile.
     *
     * @param authz authorization
     */
    void grant(Authorization authz);

    /**
     * Revoke the specified authorization for this profile.
     *
     * @param authz authorization to revoke
     */
    void revoke(Authorization authz);

    /**
     * Revoke all authorizations for this profile.
     *
     */
    void revokeAll();
}
