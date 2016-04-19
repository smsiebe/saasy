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
package org.geoint.saasy.authc;

/**
 * Thrown if a new session has attempted to be created for a subject, but a
 * known session already exists.
 *
 * @author steve_siebert
 */
public class SessionAlreadyStartedException extends AuthenticationException {

    private final String subjectGuid;
    private final String currentSessionGuid;

    public SessionAlreadyStartedException(String subjectGuid,
            String currentSessionGuid) {
        super(String.format("Session '%s' already exists for subject '%s'",
                currentSessionGuid, subjectGuid));
        this.subjectGuid = subjectGuid;
        this.currentSessionGuid = currentSessionGuid;
    }

    public String getSubjectGuid() {
        return subjectGuid;
    }

    public String getCurrentSessionGuid() {
        return currentSessionGuid;
    }

}
