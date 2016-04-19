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
 * Event indicating the session profile, and thus all authorizations, have been
 * removed.
 *
 * @author steve_siebert
 */
public final class NoSessionProfile {

    private final String subjectGuid;
    private final String sessionGuid;

    public NoSessionProfile(String subjectGuid, String sessionGuid) {
        this.subjectGuid = subjectGuid;
        this.sessionGuid = sessionGuid;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getSessionGuid() {
        return sessionGuid;
    }

    @Override
    public String toString() {
        return String.format("The session profile for subject '%s' on "
                + "session '%s' has been removed.",
                subjectGuid, sessionGuid);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.subjectGuid);
        hash = 17 * hash + Objects.hashCode(this.sessionGuid);
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
        final NoSessionProfile other = (NoSessionProfile) obj;
        if (!Objects.equals(this.subjectGuid, other.subjectGuid)) {
            return false;
        }
        if (!Objects.equals(this.sessionGuid, other.sessionGuid)) {
            return false;
        }
        return true;
    }

}
