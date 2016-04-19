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

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Indicates a security resource was registered with the
 * {@link ResourceSecurityManager}.
 *
 * @see ResourceSecurityManager
 * @author steve_siebert
 */
public final class ResourceSecurityRegistered {

    private final String namespace;
    private final String version;
    private final String type;
    private final String action;
    private final Set<String> constraints;

    public ResourceSecurityRegistered(String namespace, String version,
            String type, String action, Collection<String> constraints) {
        this.namespace = namespace;
        this.version = version;
        this.type = type;
        this.action = action;
        this.constraints = new HashSet<>(constraints); //defensive copy and make distinct
    }

    public String getNamespace() {
        return namespace;
    }

    public String getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public Set<String> getConstraints() {
        return constraints;
    }

    @Override
    public String toString() {
        return String.format("Resource security context was registered for "
                + "resource %s.%s#%s, version %s",
                namespace, type, action, version);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.namespace);
        hash = 83 * hash + Objects.hashCode(this.version);
        hash = 83 * hash + Objects.hashCode(this.type);
        hash = 83 * hash + Objects.hashCode(this.action);
        hash = 83 * hash + Objects.hashCode(this.constraints);
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
        final ResourceSecurityRegistered other = (ResourceSecurityRegistered) obj;
        if (!Objects.equals(this.namespace, other.namespace)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        if (!Objects.equals(this.constraints, other.constraints)) {
            return false;
        }
        return true;
    }

}
