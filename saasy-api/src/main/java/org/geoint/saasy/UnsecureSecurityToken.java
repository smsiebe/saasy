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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Simple {@link SecurityToken} implementation which does not support data
 * integrity or non-repudiation.
 *
 * @author steve_siebert
 */
public class UnsecureSecurityToken implements SecurityToken {

    private static final Pattern PARSER_REGEX
            = Pattern.compile("(\\{'(\\w+)':'(\\w+)'\\})");

    private final Map<String, String> attributes;
//    private static final Supplier<MessageDigest> DIGEST_SUPPLIER;
//    private static final String SHA1_ALGORITHM = "sha1";
//
//    static {
//        DIGEST_SUPPLIER = () -> {
//            try {
//                return MessageDigest.getInstance(SHA1_ALGORITHM);
//            } catch (NoSuchAlgorithmException ex) {
//                Logger.getLogger(UnsecureSecurityToken.class.getName())
//                        .log(Level.SEVERE, "SHA1 digest is not available.", ex);
//                return null;
//            }
//        };
//    }

    public UnsecureSecurityToken(Map<String, String> attributes) {
        this.attributes = Collections.unmodifiableMap(attributes);
    }

    public static SecurityToken.Builder builder() {
        return new Builder();
    }

    /**
     * Convert a string output from {@link UnsecureSecurityToken#asString()} to
     * a UnsecureSecurityToken instance.
     *
     * @param tokenString token as string
     * @return default security token instance
     * @throws UnsupportedSecurityTokenException thrown if the string format is
     * not supported by the class
     */
    public static UnsecureSecurityToken valueOf(String tokenString)
            throws UnsupportedSecurityTokenException {
        if (!tokenString.startsWith("{'attributes':[")) {
            throw new UnsupportedSecurityTokenException("Not a valid security token");
        }
        
        Matcher m = PARSER_REGEX.matcher(tokenString);
        Map<String, String> attributes = new HashMap<>();
        while (m.find()) {
            attributes.put(m.group(2), m.group(3));
        }

        return new UnsecureSecurityToken(attributes);
    }

    @Override
    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    @Override
    public Optional<String> findAttribute(String name) {
        return Optional.ofNullable(this.attributes.get(name));
    }

    @Override
    public String getAttribute(String name, Supplier<String> defaultValue) {
        return this.attributes.getOrDefault(name, defaultValue.get());
    }

    @Override
    public boolean verifyIntegrity() {
        return false;
    }

    @Override
    public boolean verifyOriginator() {
        return false;
    }

    @Override
    public String asString() {
        //simplified JSON formatted token (does not try to be JWT spec compliant}
        return new StringBuilder()
                .append("{'attributes':")
                .append(this.attributes.entrySet()
                        .stream()
                        .sorted(Entry.comparingByKey())
                        .map((e) -> String.format("{'%s':'%s'}", e.getKey(), e.getValue()))
                        .collect(Collectors.joining(",", "[", "]"))
                )
                .append("}")
                .toString();
    }

//    /**
//     * Create a hex-encoded cryptographic hash of the token contents.
//     *
//     * @return hex-encoded hash
//     */
//    private static String hashContent(UnsecureSecurityToken token) {
//        MessageDigest d = DIGEST_SUPPLIER.get();
//        d.update(token.type.getBytes(StandardCharsets.UTF_8));
//        token.attributes.entrySet()
//                .stream()
//                .sorted(Entry.comparingByKey())
//                .forEach((e) -> {
//                    d.update(e.getKey().getBytes(StandardCharsets.UTF_8));
//                    d.update(e.getValue().getBytes(StandardCharsets.UTF_8));
//                });
//        return DatatypeConverter.printHexBinary(d.digest());
//    }
    private static class Builder
            implements SecurityToken.Builder {

        private final Map<String, String> attributes = new HashMap<>();

        @Override
        public Builder attribute(String attributeName, String attributeValue) {
            attributes.put(attributeName, attributeValue);
            return this;
        }

        @Override
        public SecurityToken create() {
            return new UnsecureSecurityToken(attributes);
        }

    }
}
