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
package org.geoint.saasy.session.event;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

/**
 * Authentication event.
 *
 * @author steve_siebert
 */
public final class SubjectAuthenticated {

    private final String subjectGuid;
    private final String sessionGuid;
    private final ZonedDateTime authenticationTime;
    private final Optional<String> remoteAddress;
    private final int assuranceLevel;

    public SubjectAuthenticated(String subjectGuid, String sessionGuid,
            ZonedDateTime authenticationTime,
            int assuranceLevel, String remoteAddress) {
        this.subjectGuid = subjectGuid;
        this.sessionGuid = sessionGuid;
        this.authenticationTime = authenticationTime;
        this.remoteAddress = Optional.ofNullable(remoteAddress);
        this.assuranceLevel = assuranceLevel;
    }

    public SubjectAuthenticated(String subjectGuid, String sessionGuid,
             ZonedDateTime authenticationTime,
            int assuranceLevel) {
        this(subjectGuid, sessionGuid,  authenticationTime, assuranceLevel, null);
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getSessionGuid() {
        return sessionGuid;
    }


    public ZonedDateTime getAuthenticationTime() {
        return authenticationTime;
    }

    public Optional<String> getRemoteAddress() {
        return remoteAddress;
    }

    public int getAssuranceLevel() {
        return assuranceLevel;
    }

    @Override
    public String toString() {
        return String.format("Subject '%s' was authenticated with a credential "
                + "providing an assurance level of %d from %s at %s",
                subjectGuid, assuranceLevel,
                remoteAddress.orElse("UNKNOWN"),
                authenticationTime.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.sessionGuid);
        hash = 37 * hash + Objects.hashCode(this.authenticationTime);
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
        if (!Objects.equals(this.sessionGuid, other.sessionGuid)) {
            return false;
        }
        return Objects.equals(this.authenticationTime, other.authenticationTime);
    }

}
