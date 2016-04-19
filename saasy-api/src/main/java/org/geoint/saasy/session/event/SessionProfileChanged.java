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
 * Event indicating the {@link SessionProfile}, and thus authorizations and
 * tenant context, has changed.
 *
 * @author steve_siebert
 */
public final class SessionProfileChanged {

    private final String subjectGuid;
    private final String sessionGuid;
    private final String membershipGuid;

    public SessionProfileChanged(String subjectGuid, String sessionGuid,
            String membershipGuid) {
        this.subjectGuid = subjectGuid;
        this.sessionGuid = sessionGuid;
        this.membershipGuid = membershipGuid;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getSessionGuid() {
        return sessionGuid;
    }

    public String getMembershipGuid() {
        return membershipGuid;
    }

    @Override
    public String toString() {
        return String.format("Subject '%s' has changed its profile to '%s' "
                + "on session '%s'",
                subjectGuid, membershipGuid, sessionGuid);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.subjectGuid);
        hash = 59 * hash + Objects.hashCode(this.sessionGuid);
        hash = 59 * hash + Objects.hashCode(this.membershipGuid);
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
        final SessionProfileChanged other = (SessionProfileChanged) obj;
        if (!Objects.equals(this.subjectGuid, other.subjectGuid)) {
            return false;
        }
        if (!Objects.equals(this.sessionGuid, other.sessionGuid)) {
            return false;
        }
        if (!Objects.equals(this.membershipGuid, other.membershipGuid)) {
            return false;
        }
        return true;
    }

}
