/*
 * PersonService.java
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

    public Person updatePerson(final Person person) {
        return personRepository.save(person);
    }
}
