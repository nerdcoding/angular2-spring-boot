/*
 * PersonTestUtility.java
 *
 * Copyright (c) 2016, Tobias Koltsch. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.nerdcoding.sample.angular2.server.utility;

import org.nerdcoding.sample.angular2.server.domain.entity.person.Gender;
import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;

/**
 * Some utils for tests with a {@link Person}.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
public final class PersonTestUtils {

    public static final String TEST_USER_NAME = "cnorris";

    /** privae constructor prevents instances */
    private PersonTestUtils() {
        throw new AssertionError("no instances allowed of utility class");
    }

    /**
     * @return An instance of {@link Person} with some default values.
     */
    public static Person createTestPerson() {
        final Person person = new Person();
        person.setUsername(TEST_USER_NAME);
        person.setFirstName("Chuck");
        person.setLastName("Norris");
        person.seteMail("chuck.norris@yahoo.com");
        person.setGender(Gender.MALE);

        return person;
    }
}
