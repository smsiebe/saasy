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
package org.geoint.saasy.authc;

/**
 * A single-factor assertion used to authenticate identity.
 *
 * @author steve_siebert
 */
public interface SecurityCredential {

    /**
     * Return the
     * {@link http://csrc.nist.gov/publications/nistpubs/800-63/SP800-63V1_0_2.pdf NIST SP 800-63}/{@link http://www.whitehouse.gov/omb/memoranda/fy04/m04-04.pdf OMB Memorandum M-04-04}
     * assurance level of this credential when validated.
     *
     * @return credential assurance level when verified
     */
    int getAssuranceLevel();

    /**
     * Return the credential authentication state.
     *
     * @return true if the credential was validated (authenticated) for the
     * subject, otherwise false
     */
    boolean isAuthenticated();

    /**
     * Attempt to authenticate the credential by providing a supported
     * credential assertion.
     *
     * @param obj credential assertion value
     * @return authentication event if successfully authenticated
     * @throws AuthenticationException thrown if credential validation failed
     *
     */
    SubjectAuthenticated authenticate(Object obj) throws AuthenticationException;
    
    /**
     * Return the security credential as a subject-credential unique
     * <b>PUBLIC</b>
     * string.
     * <p>
     * The returned string must be <b>public</b> and not contain any private
     * information.
     *
     * @return public string uniquely serializing (identifying) the subject
     * credential
     */
    String asString();
}
