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

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * Bootstrap class for {@link IdentityManager}.
 * <p>
 * This class uses a {@link ServiceLoader} to discover and construct instances
 * of {@link IdentityMangager} found on the classpath of the calling thread.
 * Constructed instances of IdentityManager are not immediately initialized (see
 * method descriptions on how initialization takes place).
 *
 * @author steve_siebert
 */
public class ID {

    private static final Logger LOGGER = Logger.getLogger(ID.class.getName());
    /*
     * Optionally set JVM-default identity manager.
     * 
     * If loadAndSetDefaultManager() wasn't previously called this variable 
     * will be null.
     */
    private static IdentityManager defaultSecurityManager;

    /**
     * Returns an initialized IdentityManager instance which is accepted by the
     * provided filter.
     *
     * @param filter manager implementation filter
     * @param config manager config
     * @return initialized identity manager instance
     * @throws IdentityException if the identity manager fails to initialize
     * @throws NoIdentityManagerFoundException if acceptable identity manager is
     * found
     */
    public static IdentityManager loadIdentityManager(
            Predicate<IdentityManager> filter,
            Map<String, String> config) throws IdentityException {

        ServiceLoader<IdentityManager> loader
                = ServiceLoader.load(IdentityManager.class);

        Iterator<IdentityManager> iterator = loader.iterator();

        if (!iterator.hasNext()) {
            LOGGER.severe("No identity manager implementation was found by "
                    + "ServiceLoader.");
        }

        while (iterator.hasNext()) {
            IdentityManager idm = iterator.next();
            if (filter.test(idm)) {

                LOGGER.info(() -> String.format("IdentityMaanger implementation "
                        + "'%s' was accepted as the identity manager.",
                        idm.getClass().getName()));

                idm.initialize(config);

                LOGGER.fine(() -> String.format("IdentityManager '%s' was "
                        + "successfully initialized.", idm.getClass().getName()));
                return idm;
            }

            LOGGER.fine(() -> String.format("IdentityManager implementation '%s' "
                    + "was not accepted as the identity manager.",
                    idm.getClass().getName()));
        }

        throw new NoIdentityManagerFoundException();
    }

    /**
     * Loads a new IdentityMangaer instance and sets it as the JVM-default
     * identity manager.
     *
     * @param filter manager implementation filter
     * @param config manager config
     * @return initialized identity manager instance
     * @throws IdentityException if the identity manager fails to initialize
     * @throws NoIdentityManagerFoundException if acceptable identity manager is
     * found
     */
    public static synchronized IdentityManager loadAndSetDefaultManager(
            Predicate<IdentityManager> filter,
            Map<String, String> config) throws IdentityException {
        defaultSecurityManager = loadIdentityManager(filter, config);
        return defaultSecurityManager;
    }

    /**
     * Returns the JVM-default identity manager, if previously set, otherwise
     * throws NoIdentityManagerFoundException.
     *
     * @return JVM-default security manager, if set
     * @throws NoIdentityManagerFoundException if a JVM-default identity manager
     * was not set by previously calling 
     * {@link ID#loadAndSetDefaultManager(Predicate, Map) }
     */
    public static synchronized IdentityManager getDefaultManager()
            throws NoIdentityManagerFoundException {
        if (defaultSecurityManager == null) {
            throw new NoIdentityManagerFoundException("Default identitiy "
                    + "manager could not be retrieved, identity manager has "
                    + "not yet been set.");
        }
        return defaultSecurityManager;
    }
}
