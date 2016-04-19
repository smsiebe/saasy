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
 * An event indicating that a session subscription continues to be associated
 * with a session.
 *
 * @author steve_siebert
 */
public final class SecuritySessionSubscriberHeartbeat {

    private final String subscriberGuid;
    private final String sessionGuid;

    public SecuritySessionSubscriberHeartbeat(String subscriberGuid,
            String sessionGuid) {
        this.subscriberGuid = subscriberGuid;
        this.sessionGuid = sessionGuid;
    }

    public String getSubscriberGuid() {
        return subscriberGuid;
    }

    public String getSessionGuid() {
        return sessionGuid;
    }

    @Override
    public String toString() {
        return String.format("Subscriber '%s' sent heartbeat to session '%s'",
                subscriberGuid, sessionGuid);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.subscriberGuid);
        hash = 37 * hash + Objects.hashCode(this.sessionGuid);
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
        final SecuritySessionSubscriberHeartbeat other = (SecuritySessionSubscriberHeartbeat) obj;
        if (!Objects.equals(this.subscriberGuid, other.subscriberGuid)) {
            return false;
        }
        if (!Objects.equals(this.sessionGuid, other.sessionGuid)) {
            return false;
        }
        return true;
    }

}
