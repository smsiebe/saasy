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

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author steve_siebert
 */
public class UnsecureSecurityTokenTest {

    private static final String EXPECTED_FORMATTED
            = "{'attributes':[{'att1':'val1'},{'att2':'val2'}]}";
    private static final Map<String, String> EXPECTED_ATTRIBUTES;

    static {
        EXPECTED_ATTRIBUTES = new HashMap<>(2);
        EXPECTED_ATTRIBUTES.put("att1", "val1");
        EXPECTED_ATTRIBUTES.put("att2", "val2");
    }

    @Test
    public void testFormat() {
        UnsecureSecurityToken token
                = new UnsecureSecurityToken(EXPECTED_ATTRIBUTES);
        final String formatted = token.asString();
        assertEquals(EXPECTED_FORMATTED, formatted);
    }

    @Test
    public void testParse() throws Exception {
        UnsecureSecurityToken token = UnsecureSecurityToken.valueOf(EXPECTED_FORMATTED);
        Map<String, String> attributes = token.getAttributes();
        EXPECTED_ATTRIBUTES.entrySet()
                .forEach((e) -> {
                    assertTrue(String.format("missing attribute '%s'", e.getKey()),
                            attributes.containsKey(e.getKey()));
                    assertTrue(String.format("invalid attribute value for '%s'", e.getKey()),
                            attributes.get(e.getKey()).contentEquals(e.getValue()));
                });
        assertEquals("paring introduced attributes", attributes.size(), EXPECTED_ATTRIBUTES.size());
    }

    @Test(expected = UnsupportedSecurityTokenException.class)
    public void testInvalidParse() throws Exception {
        UnsecureSecurityToken.valueOf("junk");
    }

    @Test
    public void testBuilder() {
        SecurityToken.Builder builder = UnsecureSecurityToken.builder();
        EXPECTED_ATTRIBUTES.forEach(builder::attribute);
        SecurityToken token = builder.create();

        final Map<String, String> attributes = token.getAttributes();
        EXPECTED_ATTRIBUTES.entrySet()
                .forEach((e) -> {
                    assertTrue("missing attribute", attributes.containsKey(e.getKey()));
                    assertTrue("invalid attribute value", attributes.get(e.getKey()).contentEquals(e.getValue()));
                });
    }

}
