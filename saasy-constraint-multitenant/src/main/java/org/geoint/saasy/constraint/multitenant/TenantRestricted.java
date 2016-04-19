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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.geoint.saasy.session.SecuritySession;

/**
 * Marks a method (resource action) as one invokable only by
 * {@link SecuritySession#getTenancy() members of the specified tenant}, as
 * defined by the {@link Tenant} annotation.
 * <p>
 * {@code TenantRestricted} requires one (and only one) resource method to be
 * annotated with {@link Tenant}, defining the tenant context of the resource
 * instance.
 *
 * @see Tenant
 * @author steve_siebert
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TenantRestricted {

}
