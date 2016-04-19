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
package org.geoint.saasy;

/**
 * Thrown if there was a problem related to a tenant.
 *
 * @author steve_siebert
 */
public abstract class TenancyException extends IdentityException {

    private final String tenantGuid;

    public TenancyException(String tenantGuid) {
        this.tenantGuid = tenantGuid;
    }

    public TenancyException(String tenantGuid, String message) {
        super(message);
        this.tenantGuid = tenantGuid;
    }

    public TenancyException(String tenantGuid, String message, Throwable cause) {
        super(message, cause);
        this.tenantGuid = tenantGuid;
    }

    public TenancyException(String tenantGuid, Throwable cause) {
        super(cause);
        this.tenantGuid = tenantGuid;
    }

    public String getTenantGuid() {
        return tenantGuid;
    }

}
