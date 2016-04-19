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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Indicates a membership has been enabled.
 *
 * @author steve_siebert
 */
public final class TenantMembershipEnabled {

    private final String subjectGuid;
    private final String tenantGuid;
    private final String membershipGuid;
    private final String enabledBy;
    private final ZonedDateTime enabledTime;

    public TenantMembershipEnabled(String subjectGuid, String tenantGuid,
            String membershipGuid, String enabledBy, ZonedDateTime enabledDate) {
        this.subjectGuid = subjectGuid;
        this.tenantGuid = tenantGuid;
        this.membershipGuid = membershipGuid;
        this.enabledBy = enabledBy;
        this.enabledTime = enabledDate;
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

    public String getEnabledBy() {
        return enabledBy;
    }

    public ZonedDateTime getEnabledTime() {
        return enabledTime;
    }

    @Override
    public String toString() {
        return String.format("Membership '%s' was enabled by '%s' on '%s'.",
                membershipGuid, enabledBy, enabledTime.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.membershipGuid);
        hash = 17 * hash + Objects.hashCode(this.enabledBy);
        hash = 17 * hash + Objects.hashCode(this.enabledTime);
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
        final TenantMembershipEnabled other = (TenantMembershipEnabled) obj;
        if (!Objects.equals(this.membershipGuid, other.membershipGuid)) {
            return false;
        }
        if (!Objects.equals(this.enabledBy, other.enabledBy)) {
            return false;
        }
        if (!Objects.equals(this.enabledTime, other.enabledTime)) {
            return false;
        }
        return true;
    }

}
