/*
 * TestDataCreator.java
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

package org.nerdcoding.sample.angular2.server.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * During startup of the Spring Boot application, we read the "testData.json" form the file system and import all
 * entries into the embedded MongoDB.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@Component
public class TestDataCreator {

    private static final String TEST_DATA_FILE = "server/src/main/resources/static/testData.json";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void setUp() throws IOException {
        mongoTemplate.insert(readTestDataJsonFile(), Person.class);
    }

    private List<Person> readTestDataJsonFile() throws IOException {
        return mapper.readValue(
                new FileInputStream(new File(TEST_DATA_FILE)), new TypeReference<List<Person>>() {});
    }
}
