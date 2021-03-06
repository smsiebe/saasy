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
package org.geoint.saasy.authc.event;

import java.util.Objects;

/**
 * Event indicating a {@link SecurityCredential} has been removed from the
 * authentication options for a subject.
 *
 * @author steve_siebert
 */
public final class AuthenticationCredentialRevoked {

    private final String subjectGuid;
    private final String credentialToken;

    public AuthenticationCredentialRevoked(String subjectGuid,
            String credentialToken) {
        this.subjectGuid = subjectGuid;
        this.credentialToken = credentialToken;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getCredentialToken() {
        return credentialToken;
    }

    @Override
    public String toString() {
        return String.format("A credential was revoked from the approved "
                + "list for subject '%s'", subjectGuid);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.subjectGuid);
        hash = 23 * hash + Objects.hashCode(this.credentialToken);
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
        final AuthenticationCredentialRevoked other = (AuthenticationCredentialRevoked) obj;
        if (!Objects.equals(this.subjectGuid, other.subjectGuid)) {
            return false;
        }
        return Objects.equals(this.credentialToken, other.credentialToken);
    }

}
