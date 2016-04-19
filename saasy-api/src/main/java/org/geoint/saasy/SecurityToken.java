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

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * A serialized format of a security component.
 *
 * @author steve_siebert
 */
public interface SecurityToken {

    /**
     * An immutable key/value map of token attributes.
     *
     * @return token attributes
     */
    Map<String, String> getAttributes();

    /**
     * Retrieve the attribute value, if set on the token.
     *
     * @param name attribute name
     * @return attribute value or null if not found on the token
     */
    Optional<String> findAttribute(String name);

    /**
     * Retrieve the attribute value or default value.
     *
     * @param name attribute name
     * @param defaultValue default value generator
     * @return attribute value on the token or the default value if the
     * attribute did not exist
     */
    String getAttribute(String name, Supplier<String> defaultValue);

    /**
     * Verify the token content has not changed since created by the originator
     * (data integrity).
     *
     * @return true if the token content has not been changed, otherwise false
     */
    boolean verifyIntegrity();

    /**
     * Verify the token originator is who they say they are (non-repudiation).
     *
     * @return true if the token originator can not be repudiated
     */
    boolean verifyOriginator();

    /**
     * The security token as a String.
     * <p>
     * A security token, serialized to String, MUST be PUBLIC and not contain
     * sensitive security or personally identifiable information.
     *
     * @return token string
     */
    String asString();

    /**
     * SecurityToken builder API.
     * <p>
     * Instances of the token builder must not be required to be thread-safe.
     */
    public interface Builder {

        /**
         * Add a token attribute.
         *
         * @param attributeName attribute name
         * @param attributeValue attribute value
         * @return this builder (fluid interface)
         */
        Builder attribute(String attributeName, String attributeValue);

        /**
         * Create an instance of SecurityToken from the contents of the builder.
         *
         * @return token instance
         */
        SecurityToken create();
    }

}
