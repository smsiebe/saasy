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
package org.geoint.saasy;

import org.geoint.saasy.authz.Authorization;
import org.geoint.saasy.authz.AuthorizationProfile;
import org.geoint.saasy.event.TenantMembershipDisabled;
import org.geoint.saasy.event.TenantMembershipEnabled;
import org.geoint.saasy.event.TenantMembershipEstablished;

/**
 * A {@link SecuritySubject subject} may have zero or more memberships to a
 * {@link SecurityTenant tenant}, each having its own authorization profile.
 *
 * @author steve_siebert
 */
public interface TenantMembership {

    /**
     * The subject associated with the membership.
     *
     * @return subject
     */
    SecuritySubject getSubject();

    /**
     * Returns the subject name used within context of this membership, which
     * may be the same as the SecuritySubject or overridden.
     *
     * @return membership specific subject name
     */
    String getSubjectName();

    /**
     * The tenancy context for this membership.
     *
     * @return tenant
     */
    SecurityTenant getTenant();

    /**
     * A subject-unique membership "nickname".
     * <p>
     * This nickname MUST be UTF-8 and SHOULD be a human-consumable value.
     *
     * @return membership nickname
     */
    String getMembershipNickname();

    /**
     * Authorizations associated with this membership.
     *
     * @return authorizations
     */
    AuthorizationProfile getAuthorizations();

    /**
     * Check if the membership is active.
     * <p>
     * If the membership is not active the subject MUST NOT be able to set it to
     * the current session profile.
     *
     * @return true if active, otherwise false
     */
    boolean isEnabled();

    /**
     * Disable the membership, if enabled.
     * <p>
     * If this membership is the current profile on an active session the
     * profile MUST be disassociated with the session and the session MAY be
     * closed, but may be left open and require the subject to chose a different
     * available profile.
     *
     * @return disable event if membership was enabled and disable was
     * successful, otherwise exception is thrown
     */
    TenantMembershipDisabled disable();

    /**
     * Enable the membership, if disabled.
     *
     * @return enable event if membership was disabled and enable was
     * successful, otherwise exception is thrown
     */
    TenantMembershipEnabled enable();

    /**
     * Fluent interface to create a new tenant membership.
     * <p>
     * On successful creation of a tenant membership a
     * {@link TenantMembershipEstablished} event will be published to all
     * registered {@link SecuritySubject subject listeners}.
     */
    public interface Builder {

        /**
         * Specify a subject-unique membership nickname.
         * <p>
         * If no membership nickname is specified a default nickname will be
         * created.
         *
         * @param membershipNickname membership nickname
         * @return this builder (fluid interface)
         */
        Builder nickname(String membershipNickname);

        /**
         * Use the provided alias for the subjects name when executing in the
         * context of this membership.
         *
         * @param alias subject alias
         * @return this builder (fluid interface)
         */
        Builder subjectAlias(String alias);

        /**
         * Grant the provided authorization to the subject within the context of
         * this membership.
         *
         * @param authz authorization
         * @return this builder (fluid interface);
         */
        Builder grant(Authorization authz);

        /**
         * Grant the provided authorization to the subject within the context of
         * this membership.
         *
         * @param authz authorization
         * @return this builder (fluid interface);
         */
        Builder grant(SecurityToken authz);

        /**
         * Grant the provided authorization to the subject within the context of
         * this membership.
         *
         * @param authz authorization
         * @return this builder (fluid interface);
         */
        Builder grant(String authz);

        /**
         * The membership will be created in the disabled state.
         * <p>
         * By default new memberships are enabled.
         *
         * @return this builder (fluid interface)
         */
        Builder disabled();

        /**
         * The membership will be created in the enabled state (the default).
         *
         * @return this builder (fluid interface)
         */
        Builder enabled();

        /**
         * Create the new membership.
         * <p>
         * If the membership is successfully created a
         * {@link TenantMembershipEstablished} will be notified to registered
         * {@link SecuritySubject.Listener subject listeners} and
         * {@link SecurityTenant.Listener tenant listeners}.
         *
         * @return the new membership
         * @throws TenancyException if the membership could not be created
         */
        TenantMembershipEstablished join() throws TenancyException;
    }
}
