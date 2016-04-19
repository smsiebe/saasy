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
 * Security session creation event.
 *
 * @author steve_siebert
 */
public final class SecuritySessionOpened {

    private final String subjectGuid;
    private final String sessionGuid;
    private final ZonedDateTime createdTime;
    private final String createdBy;

    public SecuritySessionOpened(String subjectGuid, String sessionGuid,
            ZonedDateTime createdTime, String createdBy) {
        this.subjectGuid = subjectGuid;
        this.sessionGuid = sessionGuid;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getSessionGuid() {
        return sessionGuid;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public String toString() {
        return String.format("Security session '%s' was opened at '%s' by '%s'",
                sessionGuid, createdTime.format(DateTimeFormatter.ISO_DATE),
                createdBy);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.subjectGuid);
        hash = 59 * hash + Objects.hashCode(this.sessionGuid);
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
        final SecuritySessionOpened other = (SecuritySessionOpened) obj;
        if (!Objects.equals(this.subjectGuid, other.subjectGuid)) {
            return false;
        }
        if (!Objects.equals(this.sessionGuid, other.sessionGuid)) {
            return false;
        }
        return true;
    }

}
