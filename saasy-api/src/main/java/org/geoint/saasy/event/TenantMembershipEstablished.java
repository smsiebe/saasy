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
 * Event indicating a new tenant membership has been created for a subject.
 *
 * @author steve_siebert
 */
public final class TenantMembershipEstablished {

    private final String subjectGuid;
    private final String tenantGuid;
    private final String membershipName;

    public TenantMembershipEstablished(String subjectGuid, String tenantGuid,
            String membershipName) {
        this.subjectGuid = subjectGuid;
        this.tenantGuid = tenantGuid;
        this.membershipName = membershipName;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getTenantGuid() {
        return tenantGuid;
    }

    public String getMembershipName() {
        return membershipName;
    }

    @Override
    public String toString() {
        return String.format("A new membership named '%s' has been created for "
                + "subject '%s' with tenant '%s'",
                membershipName, subjectGuid, tenantGuid);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.subjectGuid);
        hash = 71 * hash + Objects.hashCode(this.tenantGuid);
        hash = 71 * hash + Objects.hashCode(this.membershipName);
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
        final TenantMembershipEstablished other = (TenantMembershipEstablished) obj;
        if (!Objects.equals(this.subjectGuid, other.subjectGuid)) {
            return false;
        }
        if (!Objects.equals(this.tenantGuid, other.tenantGuid)) {
            return false;
        }
        if (!Objects.equals(this.membershipName, other.membershipName)) {
            return false;
        }
        return true;
    }

}
