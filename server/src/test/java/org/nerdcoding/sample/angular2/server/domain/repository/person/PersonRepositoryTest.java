/*
 * PersonRepositoryTest.java
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

package org.nerdcoding.sample.angular2.server.domain.repository.person;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nerdcoding.sample.angular2.server.domain.EmbeddedMongoConfiguration;
import org.nerdcoding.sample.angular2.server.domain.entity.person.Gender;
import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;
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

    private static final String TEST_USER_NAME = "cnorris";

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() throws IOException {
        personRepository.save(createTestPerson());
    }

    @After
    public void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    public void testFindByUsername() {
        final Person found = personRepository.findByUsername(TEST_USER_NAME);
        assertNotNull(found);

    }

    private Person createTestPerson() {
        final Person person = new Person();
        person.setUsername(TEST_USER_NAME);
        person.setFirstName("Chuck");
        person.setLastName("Norris");
        person.seteMail("chuck.norris@yahoo.com");
        person.setGender(Gender.MALE);

        return person;
    }
}
