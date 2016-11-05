/*
 * PersonRepositoryTest.java
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

package org.nerdcoding.sample.angular2.server.domain.repository.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nerdcoding.sample.angular2.server.domain.EmbeddedMongoConfiguration;
import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;
import org.nerdcoding.sample.angular2.server.utility.PersonTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * JUnit test for {@link PersonRepository}.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedMongoConfiguration.class)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() throws IOException {
        personRepository.save(PersonTestUtils.createTestPerson());
    }

    @After
    public void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    public void testFindByUsername() {
        final Person found = personRepository.findByUsername(PersonTestUtils.TEST_USER_NAME);
        assertNotNull(found);
        assertEquals(PersonTestUtils.TEST_USER_NAME, found.getUsername());
    }

}
