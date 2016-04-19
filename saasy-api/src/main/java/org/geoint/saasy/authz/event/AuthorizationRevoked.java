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
 * A previously granted authorization was removed.
 *
 * @author steve_siebert
 */
public final class AuthorizationRevoked {
    
    private final String subjectGuid;
    private final String tenantGuid;
    private final String membershipGuid;
    private final String authzToken;
    private final String revokedBy;
    private final ZonedDateTime revocationTime;
    
    public AuthorizationRevoked(String subjectGuid, String tenantGuid,
            String membershipGuid, String authzToken,
            String revokedBy, ZonedDateTime revocationTime) {
        this.subjectGuid = subjectGuid;
        this.tenantGuid = tenantGuid;
        this.membershipGuid = membershipGuid;
        this.authzToken = authzToken;
        this.revokedBy = revokedBy;
        this.revocationTime = revocationTime;
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
    
    public String getRevokedBy() {
        return revokedBy;
    }
    
    public ZonedDateTime getRevocationTime() {
        return revocationTime;
    }
    
    @Override
    public String toString() {
        return String.format("Authorization '%s' has been revoked by '%s' "
                + "for subject '%s' membership '%s' with tenant '%s' at "
                + "'%s'", authzToken, revokedBy, subjectGuid, membershipGuid,
                tenantGuid, revocationTime.format(DateTimeFormatter.ISO_DATE));
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.membershipGuid);
        hash = 83 * hash + Objects.hashCode(this.authzToken);
        hash = 83 * hash + Objects.hashCode(this.revokedBy);
        hash = 83 * hash + Objects.hashCode(this.revocationTime);
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
        final AuthorizationRevoked other = (AuthorizationRevoked) obj;
        if (!Objects.equals(this.membershipGuid, other.membershipGuid)) {
            return false;
        }
        if (!Objects.equals(this.authzToken, other.authzToken)) {
            return false;
        }
        if (!Objects.equals(this.revokedBy, other.revokedBy)) {
            return false;
        }
        if (!Objects.equals(this.revocationTime, other.revocationTime)) {
            return false;
        }
        return true;
    }
    
}
