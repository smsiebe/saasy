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

import java.util.Objects;

/**
 * Event indicating that the NIST SP 800-63 assurance level has been elevated
 * for the session.
 *
 * @author steve_siebert
 */
public final class AssuranceLevelElevated {

    private final String subjectGuid;
    private final String sessionGuid;
    private final String credentialType;
    private final int assuranceLevel;

    public AssuranceLevelElevated(String subjectGuid, String sessionGuid,
            String credentialType, int assuranceLevel) {
        this.subjectGuid = subjectGuid;
        this.sessionGuid = sessionGuid;
        this.credentialType = credentialType;
        this.assuranceLevel = assuranceLevel;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getSessionGuid() {
        return sessionGuid;
    }

    public String getCredentialType() {
        return credentialType;
    }

    public int getAssuranceLevel() {
        return assuranceLevel;
    }

    @Override
    public String toString() {
        return String.format("Assurance level has been elevated to %d for "
                + "subject '%s' session '%s'",
                assuranceLevel, subjectGuid, sessionGuid);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.sessionGuid);
        hash = 97 * hash + Objects.hashCode(this.credentialType);
        hash = 97 * hash + this.assuranceLevel;
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
        final AssuranceLevelElevated other = (AssuranceLevelElevated) obj;
        if (this.assuranceLevel != other.assuranceLevel) {
            return false;
        }
        if (!Objects.equals(this.sessionGuid, other.sessionGuid)) {
            return false;
        }
        if (!Objects.equals(this.credentialType, other.credentialType)) {
            return false;
        }
        return true;
    }

}
