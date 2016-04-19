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
package org.geoint.saasy.authz;

import org.geoint.saasy.SecuritySubject;

/**
 * A security token which provides a {@link SecuritySubject} permission to 
 * invoke an action on a {@link SecurityConstraint restricted} resource.
 * <p>
 * All subject authorization tokens are provided to each resource constraint to 
 * determine if a subject is permitted to invoke the resource action.  
 * <p>
 * Authorization subclasses may either be proprietary and used only by a 
 * specific {@link SecurtityConstraint}, or may be more generic and define a 
 * security "trait" of a subject and potentially used by multiple 
 * {@link SecurityContraint constraints} to verify authorization.
 * 
 * @author steve_siebert
 */
public interface Authorization {

    

}
