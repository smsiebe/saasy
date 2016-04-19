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
package org.geoint.saasy.spi.credential;

import java.util.Map;
import org.geoint.saasy.authc.SecurityCredential;

/**
 * Lifecycle-managed authorization provider which supports
 * implementation-specific credentials by converting those credentials to
 * {@link SecurityCredential tokenizable identity assertions}, which may then be
 * verified against implementation-specific credentials to authenticate a
 * subject to a defined assurance level.
 * <p>
 * The identity management system will store the credential tokens, related to a
 * subject, and will provide this information back to the
 * SecurityCredentialProvider when to create a SecurityCredential instance for
 * authentication. How the SecurityCredential verifies the credential is
 * implementation-specific; for example a SecurityCredential implementation may
 * simply use the data within the token to authenticate a user, or the token may
 * contain just metadata and the SecurityCredential authenticates the subject
 * with an external system.
 *
 * @see SecurityCredential
 * @author steve_siebert
 * @param <C> SecurityCredential created by this provider
 */
public interface SecurityCredentialProvider<C extends SecurityCredential> {

    /**
     * Initializes the provider.
     *
     * @param properties provider properties
     */
    void initialize(Map<String, String> properties);

    /**
     * Converts an implementation-specific credential object into a provider
     * {@link SecurityCredential}.
     *
     * @param credentialObject implementation-specific credential object
     * @return provider security credential
     * @throws UnsupportedCredentialException thrown if the provider does not
     * support this implementation-specified credential
     * @throws IllegalStateException thrown if the provider is not initialized
     */
    C asCredential(Object credentialObject)
            throws UnsupportedCredentialException, IllegalStateException;

    /**
     * Synchronously shut down the credential provider.
     */
    void shutdown();
}
