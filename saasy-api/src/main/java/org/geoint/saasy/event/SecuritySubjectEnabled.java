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
package org.geoint.saasy.event;

import java.util.Objects;

/**
 * Indicates a SecuritySubject has been enabled and sessions may be created for
 * the subject.
 *
 * @author steve_siebert
 */
public class SecuritySubjectEnabled {

    private final String subjectGuid;
    private final String enabledBy;

    public SecuritySubjectEnabled(String subjectGuid, String enabledBy) {
        this.subjectGuid = subjectGuid;
        this.enabledBy = enabledBy;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getEnabledBy() {
        return enabledBy;
    }

    @Override
    public String toString() {
        return String.format("Subject '%s' was enabled by '%s'",
                subjectGuid, enabledBy);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.subjectGuid);
        hash = 41 * hash + Objects.hashCode(this.enabledBy);
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
        final SecuritySubjectEnabled other = (SecuritySubjectEnabled) obj;
        if (!Objects.equals(this.subjectGuid, other.subjectGuid)) {
            return false;
        }
        if (!Objects.equals(this.enabledBy, other.enabledBy)) {
            return false;
        }
        return true;
    }

}
