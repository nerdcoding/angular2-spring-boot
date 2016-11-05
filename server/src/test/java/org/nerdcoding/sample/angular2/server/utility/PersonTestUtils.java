/*
 * PersonTestUtility.java
 *
 * Copyright (c) 2016, Tobias Koltsch. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/lgpl.txt>.
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
