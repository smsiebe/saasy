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

/**
 * Annotates the idempotent and safe resource method (action) that returns the
 * tenant which owns the resource as a String (ie GUID) or an object instance
 * which returns a unique identifier from its {@link Object#toString()} method.
 * <p>
 * This annotation works in concert with {@link TenantRestricted}, which
 * identifies resource actions which are restricted by this entity. For tenant
 * restrictions to work one (and only one) method must be annotated
 * {@code Tenant}.
 * <p>
 * A class may only have one method annotated with {@code Tenant}, but may have
 * zero or more methods annotated with {@link TenantRestricted}.
 *
 * @see TenantRestricted
 * @author steve_siebert
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Tenant {

}
