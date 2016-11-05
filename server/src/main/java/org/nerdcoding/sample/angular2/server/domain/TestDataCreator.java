/*
 * TestDataCreator.java
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

package org.nerdcoding.sample.angular2.server.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;
import org.nerdcoding.sample.angular2.server.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * During startup of the Spring Boot application, we read the "personTestData.json" form the file system and import all
 * entries into the embedded MongoDB.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@Component
public class TestDataCreator {

    private static final String PERSON_TEST_DATA_FILE = "server/src/main/resources/static/personTestData.json";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void setUp() throws IOException {
        // Insert Person test data.
        mongoTemplate.insert(readPersonTestDataJsonFile(), Person.class);
        // Insert User test data.
        mongoTemplate.insert(createTestUser());
    }

    private List<Person> readPersonTestDataJsonFile() throws IOException {
        return mapper.readValue(
                new FileInputStream(new File(PERSON_TEST_DATA_FILE)), new TypeReference<List<Person>>() {});
    }

    private User createTestUser() throws IOException {
        final User testUser = new User();
        testUser.setUsername("admin");
        testUser.setPassword("admin");
        return testUser;
    }
}
