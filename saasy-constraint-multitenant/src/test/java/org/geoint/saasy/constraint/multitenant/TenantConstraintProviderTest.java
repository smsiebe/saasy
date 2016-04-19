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

import org.geoint.saasy.authz.InvalidConstraintDefinitionException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author steve_siebert
 */
public class TenantConstraintProviderTest {

    /**
     * Test that a constraint is created for a {@link TenantRestricted}
     * annotated method.
     *
     * @throws Exception
     */
    @Test
    public void testTenantConstraintCreated() throws Exception {

        TenantConstraintProvider provider = new TenantConstraintProvider();
        TenantConstraint[] constraints = provider
                .actionConstraints(MockMultitenantResource.class.getMethod("increment"));

        assertTrue(constraints.length == 1);
        assertEquals(MockMultitenantResource.class.getName(),
                constraints[0].getResourceClassName());
        assertEquals("getTenantGuid", constraints[0].getTenantMethodName());
    }

    /**
     * Test that a constraint is not created for a method that is not annotated
     * with {@link TenantRestricted}.
     *
     * @throws Exception
     */
    @Test
    public void testTenantConstraintNotCreated() throws Exception {
        TenantConstraintProvider provider = new TenantConstraintProvider();
        assertEquals(0,
                provider.actionConstraints(
                        MockMultitenantResource.class.getMethod("getResourceGuid")
                ).length);
    }

    /**
     * Test that an exception is thrown if the resource has a
     * {@link TenantRestricted} annotated method but no {@link Tenant} annotated
     * method.
     *
     * @throws Exception
     */
    @Test(expected = InvalidConstraintDefinitionException.class)
    public void testExceptionOnMissingTenant() throws Exception {
        TenantConstraintProvider provider = new TenantConstraintProvider();
        provider.actionConstraints(MockMissingTenantResource.class
                .getMethod("restrictedAction"));
    }

    /**
     * Test that an authorization object is created for a valid constraint.
     *
     * @throws Exception
     */
    @Test
    public void testTenantAuthorizationCreated() throws Exception {
        
        TenantConstraintProvider provider = new TenantConstraintProvider();
        final TenantConstraint constraint = provider
                .actionConstraints(MockMultitenantResource.class
                        .getMethod("increment"))[0];
        
        final TenantAuthorization authz = provider.createAuthorization(constraint);
        assertNotNull(authz);
        
    }

}
