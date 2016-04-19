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
 * Indicates that a session property value has changed.
 *
 * @author steve_siebert
 */
public final class SecuritySessionPropertyChanged {

    private final String subjectGuid;
    private final String sessionGuid;
    private final String propertyName;
    private final String propertyValue;

    public SecuritySessionPropertyChanged(String subjectGuid, String sessionGuid,
            String propertyName, String propertyValue) {
        this.subjectGuid = subjectGuid;
        this.sessionGuid = sessionGuid;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getSessionGuid() {
        return sessionGuid;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    @Override
    public String toString() {
        return String.format("Property '%s' on session '%s' has changed.",
                propertyName, sessionGuid);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.sessionGuid);
        hash = 83 * hash + Objects.hashCode(this.propertyName);
        hash = 83 * hash + Objects.hashCode(this.propertyValue);
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
        final SecuritySessionPropertyChanged other = (SecuritySessionPropertyChanged) obj;
        if (!Objects.equals(this.sessionGuid, other.sessionGuid)) {
            return false;
        }
        if (!Objects.equals(this.propertyName, other.propertyName)) {
            return false;
        }
        if (!Objects.equals(this.propertyValue, other.propertyValue)) {
            return false;
        }
        return true;
    }

}
