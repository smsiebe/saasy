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
package org.geoint.saasy.constraint.multitenant;

import java.util.Objects;
import java.util.UUID;

/**
 * Mock multi-tenant resource used for testing.
 * <p>
 * Instances are not thread-safe.
 *
 * @author steve_siebert
 */
public class MockMultitenantResource {

    private final String tenantGuid;
    private final String resourceGuid;
    private int increment;

    public MockMultitenantResource(String tenantGuid, String resourceGuid) {
        this.tenantGuid = tenantGuid;
        this.resourceGuid = resourceGuid;
    }

    public static MockMultitenantResource random() {
        return new MockMultitenantResource(UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
    }

    public static MockMultitenantResource tenantRandom(String tenantGuid) {
        return new MockMultitenantResource(tenantGuid,
                UUID.randomUUID().toString());
    }

    @Tenant
    public String getTenantGuid() {
        return tenantGuid;
    }

    public String getResourceGuid() {
        return resourceGuid;
    }

    @TenantRestricted
    public void increment() {
        increment++;
    }

    public int getIncrement() {
        return increment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.tenantGuid);
        hash = 47 * hash + Objects.hashCode(this.resourceGuid);
        hash = 47 * hash + this.increment;
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
        final MockMultitenantResource other = (MockMultitenantResource) obj;
        if (this.increment != other.increment) {
            return false;
        }
        if (!Objects.equals(this.tenantGuid, other.tenantGuid)) {
            return false;
        }
        if (!Objects.equals(this.resourceGuid, other.resourceGuid)) {
            return false;
        }
        return true;
    }

}
