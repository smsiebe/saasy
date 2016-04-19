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

/**
 * Session close event.
 * 
 * @author steve_siebert
 */
public final class SecuritySessionClosed {

    private final String subjectGuid;
    private final String sessionGuid;
    private final String closedBy;
    private final ZonedDateTime closeTime;

    public SecuritySessionClosed(String subjectGuid, String sessionGuid,
            String closedBy, ZonedDateTime closeTime) {
        this.subjectGuid = subjectGuid;
        this.sessionGuid = sessionGuid;
        this.closedBy = closedBy;
        this.closeTime = closeTime;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getSessionGuid() {
        return sessionGuid;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public ZonedDateTime getCloseTime() {
        return closeTime;
    }

    @Override
    public String toString() {
        return String.format("Security session '%s' was closed by '%s' at '%s",
                sessionGuid, closedBy,
                closeTime.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.subjectGuid);
        hash = 67 * hash + Objects.hashCode(this.sessionGuid);
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
        final SecuritySessionClosed other = (SecuritySessionClosed) obj;
        if (!Objects.equals(this.subjectGuid, other.subjectGuid)) {
            return false;
        }
        if (!Objects.equals(this.sessionGuid, other.sessionGuid)) {
            return false;
        }
        return true;
    }

}
