/*
 * PersonServiceTest.java
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

package org.nerdcoding.sample.angular2.server.service.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nerdcoding.sample.angular2.server.domain.EmbeddedMongoConfiguration;
import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;
import org.nerdcoding.sample.angular2.server.domain.repository.person.PersonRepository;
import org.nerdcoding.sample.angular2.server.service.ServiceConfiguration;
import org.nerdcoding.sample.angular2.server.utility.PersonTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * JUnit test for {@link PersonService}.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmbeddedMongoConfiguration.class, ServiceConfiguration.class })
public class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    @Before
    public void setUp() {
        personRepository.save(PersonTestUtils.createTestPerson());
    }

    @After
    public void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    public void testFindByUsername() {
        final Person found = personService.findByUsername(PersonTestUtils.TEST_USER_NAME);
        assertNotNull(found);
        assertEquals(PersonTestUtils.TEST_USER_NAME, found.getUsername());
    }

    @Test
    public void testFindAllPersons() {
        final List<Person> found = personService.findAllPersons();
        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals(PersonTestUtils.TEST_USER_NAME, found.iterator().next().getUsername());
    }
}
