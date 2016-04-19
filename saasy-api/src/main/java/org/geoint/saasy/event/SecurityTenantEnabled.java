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
 * Security tenant was enabled.
 *
 * @author steve_siebert
 */
public final class SecurityTenantEnabled {

    private final String tenantGuid;
    private final String enabledBy;
    private final ZonedDateTime enabledTime;

    public SecurityTenantEnabled(String tenantGuid, String enabledBy, ZonedDateTime enabledTime) {
        this.tenantGuid = tenantGuid;
        this.enabledBy = enabledBy;
        this.enabledTime = enabledTime;
    }

    public String getTenantGuid() {
        return tenantGuid;
    }

    public String getEnabledBy() {
        return enabledBy;
    }

    public ZonedDateTime getEnabledTime() {
        return enabledTime;
    }

    @Override
    public String toString() {
        return String.format("Tenant '%s' was enabled by '%s' at '%s'.",
                tenantGuid, enabledBy,
                enabledTime.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.tenantGuid);
        hash = 59 * hash + Objects.hashCode(this.enabledBy);
        hash = 59 * hash + Objects.hashCode(this.enabledTime);
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
        final SecurityTenantEnabled other = (SecurityTenantEnabled) obj;
        if (!Objects.equals(this.tenantGuid, other.tenantGuid)) {
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
