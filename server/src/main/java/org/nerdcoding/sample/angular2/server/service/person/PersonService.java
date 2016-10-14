/*
 * PersonService.java
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

import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;
import org.nerdcoding.sample.angular2.server.domain.repository.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that handles operation on {@link org.nerdcoding.sample.angular2.server.domain.entity.person.Person}.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person findByUsername(final String username) {
        return personRepository.findByUsername(username);
    }

    public List<Person> findAllPersons() {
        // TODO introduce pagination
        return personRepository.findAll();
    }
}
