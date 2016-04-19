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

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

/**
 * Event detailing the successful authentication of a subject.
 *
 * @author steve_siebert
 */
public class SubjectAuthenticated implements Serializable {

    private final String subjectGuid;
    private final String credentialType;
    private final ZonedDateTime authenticationTime;
    private final Optional<String> remoteAddress;
    private final int assuranceLevel;

    public SubjectAuthenticated(String subjectGuid, String credentialType,
            ZonedDateTime authenticationTime, int assuranceLevel, String remoteAddress) {
        this.subjectGuid = subjectGuid;
        this.credentialType = credentialType;
        this.authenticationTime = authenticationTime;
        this.assuranceLevel = assuranceLevel;
        this.remoteAddress = Optional.ofNullable(remoteAddress);
    }

    public SubjectAuthenticated(String subjectGuid, String credentialType,
            ZonedDateTime authenticationTime, int assuranceLevel) {
        this(subjectGuid, credentialType, authenticationTime, assuranceLevel, null);
    }

    /**
     * Globally unique subject identifier.
     *
     * @return subject identifier
     */
    public String getSubjectGuid() {
        return subjectGuid;
    }

    /**
     * Class name of the {@link SecurityCredential} used to authenticate.
     *
     * @return credential class name
     */
    public String getCredentialType() {
        return credentialType;
    }

    /**
     * Time of successful authentication.
     *
     * @return authentication time
     */
    public ZonedDateTime getAuthenticationTime() {
        return authenticationTime;
    }

    /**
     * Client/remote address used to authenticate, if known.
     *
     * @return client ip address or null
     */
    public Optional<String> getRemoteAddress() {
        return remoteAddress;
    }

    /**
     * Total NIST assurance level of the subject at the time of authentication 
     * (which may be higher than the assurance level of this credential).
     *
     * @return resultant assurance level
     */
    public int getAssuranceLevel() {
        return assuranceLevel;
    }

    @Override
    public String toString() {
        return String.format("Subject '%s' was authenticated at '%s' at NIST "
                + "assurance level %d", this.subjectGuid,
                this.authenticationTime.format(DateTimeFormatter.ISO_DATE),
                this.assuranceLevel);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.subjectGuid);
        hash = 23 * hash + Objects.hashCode(this.credentialType);
        hash = 23 * hash + Objects.hashCode(this.authenticationTime);
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
        final SubjectAuthenticated other = (SubjectAuthenticated) obj;
        if (!Objects.equals(this.subjectGuid, other.subjectGuid)) {
            return false;
        }
        if (!Objects.equals(this.credentialType, other.credentialType)) {
            return false;
        }
        if (!Objects.equals(this.authenticationTime, other.authenticationTime)) {
            return false;
        }
        return true;
    }

}
