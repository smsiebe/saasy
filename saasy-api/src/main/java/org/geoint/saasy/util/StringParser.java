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
package org.geoint.saasy.util;

/**
 * Converts serialized data into a object instance.
 *
 * @author steve_siebert
 * @param <T> type created when parsed successfully
 */
@FunctionalInterface
public interface StringParser<T> {

    /**
     * Parse serialized data.
     *
     * @param formatted formatted object data to parse
     * @return object instance, which may be null
     * @throws StringParseException thrown if parsing the serialized data failed
     */
    T parse(String formatted) throws StringParseException;
}
