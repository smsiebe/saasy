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
 * Indicates a membership has been disabled.
 *
 * @author steve_siebert
 */
public final class TenantMembershipDisabled {

    private final String subjectGuid;
    private final String tenantGuid;
    private final String membershipGuid;
    private final String disabledBy;
    private final ZonedDateTime disabledTime;

    public TenantMembershipDisabled(String subjectGuid, String tenantGuid,
            String membershipGuid, String disabledBy, ZonedDateTime disabledTime) {
        this.subjectGuid = subjectGuid;
        this.tenantGuid = tenantGuid;
        this.membershipGuid = membershipGuid;
        this.disabledBy = disabledBy;
        this.disabledTime = disabledTime;
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

    public String getDisabledBy() {
        return disabledBy;
    }

    public ZonedDateTime getDisabledTime() {
        return disabledTime;
    }

    @Override
    public String toString() {
        return String.format("Tenant membership '%s' has been disabled by '%s' "
                + "on '%s'.", membershipGuid, disabledBy,
                disabledTime.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.membershipGuid);
        hash = 37 * hash + Objects.hashCode(this.disabledBy);
        hash = 37 * hash + Objects.hashCode(this.disabledTime);
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
        final TenantMembershipDisabled other = (TenantMembershipDisabled) obj;
        if (!Objects.equals(this.membershipGuid, other.membershipGuid)) {
            return false;
        }
        if (!Objects.equals(this.disabledBy, other.disabledBy)) {
            return false;
        }
        if (!Objects.equals(this.disabledTime, other.disabledTime)) {
            return false;
        }
        return true;
    }

}
