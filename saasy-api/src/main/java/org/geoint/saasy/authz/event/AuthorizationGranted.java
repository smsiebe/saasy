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
package org.geoint.saasy.authz.event;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Event detailing a new authorization grant.
 *
 * @author steve_siebert
 */
public final class AuthorizationGranted {

    private final String subjectGuid;
    private final String tenantGuid;
    private final String membershipGuid;
    private final String authzToken;
    private final String grantedBy;
    private final ZonedDateTime grantedTime;

    public AuthorizationGranted(String subjectGuid, String tenantGuid,
            String membershipGuid, String authzToken,
            String grantedBy, ZonedDateTime grantedTime) {
        this.subjectGuid = subjectGuid;
        this.tenantGuid = tenantGuid;
        this.membershipGuid = membershipGuid;
        this.authzToken = authzToken;
        this.grantedBy = grantedBy;
        this.grantedTime = grantedTime;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getTenantGuid() {
        return tenantGuid;
    }

    public String getMembershipGuid() {
        return membershipGuid;
    }

    public String getAuthzToken() {
        return authzToken;
    }

    public String getGrantedBy() {
        return grantedBy;
    }

    public ZonedDateTime getGrantedTime() {
        return grantedTime;
    }

    @Override
    public String toString() {
        return String.format("Authorization '%s' was granted to subject '%s' "
                + ", member of '%s' with membership id of '%s' by '%s' on '%s'",
                authzToken, subjectGuid, tenantGuid, membershipGuid,
                grantedBy, grantedTime.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.membershipGuid);
        hash = 89 * hash + Objects.hashCode(this.authzToken);
        hash = 89 * hash + Objects.hashCode(this.grantedBy);
        hash = 89 * hash + Objects.hashCode(this.grantedTime);
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
        final AuthorizationGranted other = (AuthorizationGranted) obj;
        if (!Objects.equals(this.membershipGuid, other.membershipGuid)) {
            return false;
        }
        if (!Objects.equals(this.authzToken, other.authzToken)) {
            return false;
        }
        if (!Objects.equals(this.grantedBy, other.grantedBy)) {
            return false;
        }
        if (!Objects.equals(this.grantedTime, other.grantedTime)) {
            return false;
        }
        return true;
    }

}
