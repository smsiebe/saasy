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
package org.geoint.saasy.session;

/**
 * Exposes additional behavior, such as mutable operations, to session
 * subscribers.
 *
 * @author steve_siebert
 */
public interface SubscribedSession extends SecuritySession {

    /**
     * Sets the session property value, return the old value or null.
     *
     * @param name session property name
     * @param value new session property value
     * @return old session property value or null if not previously set
     */
    String putProperty(String name, String value);

    /**
     * Generates a one-time-use nonce unique to the session subscriber.
     *
     * @return one-time use nonce
     */
    String generateNonce();

    /**
     * Manually send a subscription heatbeat, preventing the subscription 
     * from timing out.
     */
    void heartbeat();
    
    
}
