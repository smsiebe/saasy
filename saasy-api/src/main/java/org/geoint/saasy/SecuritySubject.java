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

import java.util.Optional;
import java.util.Set;
import org.geoint.saasy.authc.AuthenticationException;
import org.geoint.saasy.authc.SecurityCredential;
import org.geoint.saasy.authc.SubjectAuthenticated;
import org.geoint.saasy.event.SecuritySubjectDisabled;
import org.geoint.saasy.event.SecuritySubjectEnabled;
import org.geoint.saasy.authc.event.AuthenticationCredentialGranted;
import org.geoint.saasy.authc.event.AuthenticationCredentialRevoked;
import org.geoint.saasy.event.TenantMembershipEstablished;
import org.geoint.saasy.session.SecuritySession;
import org.geoint.saasy.session.event.SecuritySessionOpened;

/**
 * The unique entity being identified.
 *
 * @author steve_siebert
 */
public interface SecuritySubject {

    /**
     * The globally unique subject identifier, which must not change for the
     * subject or be reused if the subject is disabled.
     *
     * @return unique subject identifier
     */
    String getGuid();

    /**
     * Human-readable name of the subject.
     * <p>
     * The name of a subject may be overridden within the context of a
     * {@link SecuritySession}. This name is used by default if the session does
     * not override with subject name.
     *
     * @return subject name used by default
     */
    String getName();

    /**
     * Collection of known credentials that may be used to authenticate the
     * subject.
     *
     * @return subject credentials
     */
    Set<SecurityCredential> getCredentials();

    /**
     * Grant permission for a subject to authenticate identity with the provided
     * credential.
     *
     * {@link AuthenticationCredentialGranted} event to be notified to all
     * registered {@link SecuritySubjectListener listeners}.
     *
     * @param credential credential
     * @return grant event
     */
    AuthenticationCredentialGranted grantCredential(
            SecurityCredential credential);

    /**
     * Remove a security credential as an authorization option.
     * <p>
     * Successful removal of the credential will result in the
     * {@link AuthenticationCredentialRevoked} event to be notified to all
     * registered {@link SecuritySubjectListener listeners}.
     *
     * @param credential credential to remove
     * @return revoke event
     */
    AuthenticationCredentialRevoked revokeCredential(SecurityCredential credential);

    /**
     * Return the last successful authentication event for this subject.
     *
     * @return last authentication even or null if never successfully
     * authenticated
     */
    Optional<SubjectAuthenticated> getLastAuthenticated();

    /**
     * The total number of failed authentication attempts since the last
     * successful authentication.
     *
     * @return number of failed login attempts since last logged in successfully
     */
    int getNumFailedAttempts();

    /**
     * Active state for this subject.
     *
     * @return true if enabled/active, otherwise false.
     */
    boolean isEnabled();

    /**
     * Disables a subject if it is currently enabled.
     * <p>
     * If a session is open, disabling the subject must close the session.
     * <p>
     * A subject must not be able to authenticate if disabled.
     * <p>
     * Successful disabling of the subject will result in the
     * {@link SecuritySubjectDisabled} event to be published to all registered
     * {@link SecuritySubjectListener listeners}.
     * @return enable event, if subject was previously enabled and was 
     * successfully disabled; otherwise exception is thrown
     */
    SecuritySubjectDisabled disable();

    /**
     * Enabled a subject if it is currently disabled.
     * <p>
     * Successful disabling of the subject will result in the
     * {@link SecuritySubjectEnabled} event to be published to all registered
     * {@link SecuritySubjectListener listeners}.
     * @return disable event, if subject was previously disabled and was 
     * successfully enabled; otherwise exception is thrown
     */
    SecuritySubjectEnabled enable();

    /**
     * Returns a membership builder which, on successful creation of a
     * membership, will result in a new {@link TenantMembership} to be added to
     * this subject.
     * <p>
     * If the membership is successfully added a
     * {@link TenantMembershipEstablished} event will be published to
     *
     * @param tenant tenant
     * @return membership builder
     */
    TenantMembership.Builder createMembership(SecurityTenant tenant);

    /**
     * Returns all subject memberships.
     *
     * @return memberships
     */
    Set<TenantMembership> getMemerships();

    /**
     * Checks if there is known open session for this subject.
     *
     * @return true if there is known open session, otherwise false
     */
    boolean isSessionOpen();

    /**
     * Returns the current session for the subject or null if it does not
     * currently exist.
     *
     * @return session or null
     */
    Optional<SecuritySession> currentSession();

    /**
     * Attempt to authenticate user against all user credentials with provided
     * credential object.
     * <p>
     * Credential object can be any object and is checked against every subject
     * {@link SecurityCredential} instance until it is authenticated or no
     * SecurityCredentials remain.
     * <p>
     * If a {@link SecuritySession session} is not open, a new session is
     * created upon successful authentication. Upon successful creation of a new
     * session a {@link SecuritySessionOpened} event will be published to all
     * registered {@link SecuritySubject.Listener listeners}.
     *
     * @param cred credential object
     * @return successful authentication event
     * @throws AuthenticationException thrown if authentication failed
     */
    SubjectAuthenticated authenticate(Object cred) throws AuthenticationException;

    /**
     * Returns the name of the last used membership, if previously
     * authenticated.
     *
     * @return membership name or null
     */
    Optional<String> getLastUsedMembershipName();

    /**
     * Registers a listener.
     *
     * @param listener listener
     */
    void addListener(SecuritySubject.Listener listener);

    /**
     * Removes a listener.
     *
     * @param listener listener
     */
    void removeListener(SecuritySubject.Listener listener);

    /**
     * Callback listener interface to receive events associated with a
     * {@link SecuritySubject}.
     *
     * @see SecuritySubject#addListener(SecuritySubjectListener)
     * @author steve_siebert
     */
    public interface Listener {

        default void credentialAdded(AuthenticationCredentialGranted cred) {
            //default do nothing
        }

        default void credentialRemoved(AuthenticationCredentialRevoked cred) {
            //default do nothing
        }

        default void disabled(SecuritySubjectDisabled disabled) {
            //default do nothing
        }

        default void enabled(SecuritySubjectEnabled enabled) {
            //default do nothing
        }

        default void membershipEstablished(TenantMembershipEstablished mem) {
            //default do nothing
        }
    }

}
