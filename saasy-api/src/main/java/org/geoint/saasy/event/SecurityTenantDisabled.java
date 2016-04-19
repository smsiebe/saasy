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
 * A tenant was disabled.
 *
 * @author steve_siebert
 */
public final class SecurityTenantDisabled {

    private final String tenantGuid;
    private final String disabledBy;
    private final ZonedDateTime disabledTime;

    public SecurityTenantDisabled(String tenantGuid, String disabledBy,
            ZonedDateTime disabledTime) {
        this.tenantGuid = tenantGuid;
        this.disabledBy = disabledBy;
        this.disabledTime = disabledTime;
    }

    public String getTenantGuid() {
        return tenantGuid;
    }

    public String getDisabledBy() {
        return disabledBy;
    }

    public ZonedDateTime getDisabledTime() {
        return disabledTime;
    }

    @Override
    public String toString() {
        return String.format("Tenant '%s' was disabled by '%s' at '%s'",
                tenantGuid, disabledBy,
                disabledTime.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.tenantGuid);
        hash = 71 * hash + Objects.hashCode(this.disabledBy);
        hash = 71 * hash + Objects.hashCode(this.disabledTime);
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
        final SecurityTenantDisabled other = (SecurityTenantDisabled) obj;
        if (!Objects.equals(this.tenantGuid, other.tenantGuid)) {
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
