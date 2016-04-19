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

import org.geoint.saasy.session.event.SecuritySessionClosed;
import java.time.ZonedDateTime;
import java.util.function.Consumer;
import org.geoint.saasy.SecuritySubject;

/**
 * A management interface for a {@link SubscribedSession session subscriber}.
 * 
 * @author steve_siebert
 */
public interface SessionSubscriber {
    
    /**
     * The subject of the subscription system.
     * <p>
     * The subject returned is not necessarily (or normally) the subject 
     * of the session but rather of the subscriber.  This most often would 
     * be the application or service that is subscribed to the session.
     * 
     * @return session subscriber 
     */
    SecuritySubject getSubscriber();
    
    /**
     * Time the subscription was created.
     * 
     * @return subscription created time
     */
    ZonedDateTime getCreatedTime();
    
    /**
     * Time the subscription was/will be closed.
     * 
     * @return past or future time the subscription was/will be closed
     */
    ZonedDateTime getClosedTime();
    
    /**
     * Last time the subscription was active on the session.
     * 
     * @return last time the subscription operated on the session
     */
    ZonedDateTime getLastActivityTime();
    
    /**
     * Check if the subscriber is active.
     * 
     * @return true if subscriber is active, otherwise false
     */
    boolean isActive();
    
    /**
     * Sets a callback that receives a notification if the session subscription 
     * is active when the session is closed.
     * 
     * @param callback session close callback
     */
    void setSessionCloseHandler(Consumer<SecuritySessionClosed> callback);
    
    /**
     * Terminates the session subscription.
     */
    void close();
}
