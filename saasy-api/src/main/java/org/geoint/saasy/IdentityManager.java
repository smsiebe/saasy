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

import java.util.Map;
import java.util.Optional;
import org.geoint.saasy.authc.SecurityCredential;
import org.geoint.saasy.session.SecuritySession;

/**
 * Manages the identity/security within an application.
 *
 * @author steve_siebert
 */
public interface IdentityManager {

    /**
     * Initialize the identity manager.
     *
     * @param config manager config
     * @throws IdentityException if the manage could not be initialized
     */
    void initialize(Map<String, String> config) throws IdentityException;

    /**
     * The security session for the application.
     *
     * @return application (system) security session
     */
    SecuritySession getSystemSession();

    /**
     * Returns the security session associated with the calling thread.
     *
     * @return security session associated with current thread
     */
    SecuritySession getExecutionSession();

    /**
     * Sets the security session associated for the calling thread.
     *
     * @param sub execution thread security session, may be null
     */
    void setExecutionSession(SecuritySession sub);

    /**
     * Session details for the specified session GUID, or null if no session 
     * details are available.
     * 
     * @param sessionGuid session guid
     * @return session details
     */
    Optional<SecuritySession> findSession(String sessionGuid);
    
    /**
     * Return the subject details for the GUID.
     *
     * @param subjectGuid subject GUID
     * @return subject details or null
     */
    Optional<SecuritySubject> findSubject(String subjectGuid);

    /**
     * Attempts to retrieve a SecuritySubject associated with credentials.
     *
     * @param cred security credentials
     * @return subject, if discoverable from credential and is known to the
     * identity manager
     */
    Optional<SecuritySubject> findSubject(SecurityCredential cred);

    /**
     * Return tenant details by GUID.
     *
     * @param tenantGuid tenant GUID
     * @return tenant identifier
     */
    Optional<SecurityTenant> findTenant(String tenantGuid);

    /**
     * Synchronously shut down the identity manager.
     */
    void shutdown();

}
