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
 * Thrown if formatting of a data type to String failed.
 *
 * @author steve_siebert
 */
public class StringFormatException extends Exception {

    public StringFormatException(Class<?> type) {
        super(defaultMessage(type));
    }

    public StringFormatException(String message) {
        super(message);
    }

    public StringFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public StringFormatException(Class<?> type, Throwable cause) {
        super(defaultMessage(type), cause);
    }

    private static String defaultMessage(Class<?> type) {
        return String.format("Instance of class '%s' could not be formatted.",
                type.getName());
    }
}
