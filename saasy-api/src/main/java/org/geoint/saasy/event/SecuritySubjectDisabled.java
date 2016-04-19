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
 * Indicates a subject has been disabled.
 *
 * @author steve_siebert
 */
public final class SecuritySubjectDisabled {

    private final String subjectGuid;
    private final String disabledBy;
    private final String reason;

    public SecuritySubjectDisabled(String subjectGuid, String disabledBy, String reason) {
        this.subjectGuid = subjectGuid;
        this.disabledBy = disabledBy;
        this.reason = reason;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getDisabledBy() {
        return disabledBy;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return String.format("Subject '%s' has been disabled by '%s' due to '%s'.",
                subjectGuid, disabledBy, reason);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.subjectGuid);
        hash = 41 * hash + Objects.hashCode(this.disabledBy);
        hash = 41 * hash + Objects.hashCode(this.reason);
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
        final SecuritySubjectDisabled other = (SecuritySubjectDisabled) obj;
        if (!Objects.equals(this.subjectGuid, other.subjectGuid)) {
            return false;
        }
        if (!Objects.equals(this.disabledBy, other.disabledBy)) {
            return false;
        }
        if (!Objects.equals(this.reason, other.reason)) {
            return false;
        }
        return true;
    }

}
