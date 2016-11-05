/*
 * PersonController.java
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

package org.nerdcoding.sample.angular2.server.web.rest.person;


import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;
import org.nerdcoding.sample.angular2.server.service.person.PersonService;
import org.nerdcoding.sample.angular2.server.web.exception.CommonRestError;
import org.nerdcoding.sample.angular2.server.web.exception.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller handles the {@link Person} resource.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@RestController
public class PersonRestController {

    private Logger LOG = LoggerFactory.getLogger(PersonRestController.class);

    private static final String MAPPING_PREFIX = "/persons";

    @Autowired
    private PersonService personService;

    @RequestMapping(value = MAPPING_PREFIX + "/{username}", method = RequestMethod.GET)
    public Person getPersonByUsername(@PathVariable final String username) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("GET request on " + MAPPING_PREFIX +" with username " + username);
        }

        final Person result = personService.findByUsername(username);
        if (result == null) {
            throw new PersonNotFoundException(username);
        }

        return result;
    }

    @RequestMapping(value = PersonRestController.MAPPING_PREFIX, method = RequestMethod.GET)
    public List<Person> getAllPersons() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("GET request on " + MAPPING_PREFIX);
        }

        return personService.findAllPersons();
    }

    @RequestMapping(value = PersonRestController.MAPPING_PREFIX, method = RequestMethod.PUT)
    public ResponseEntity<String> updatePerson(@RequestBody final Person person) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("PUT request on " + MAPPING_PREFIX);
        }

        personService.updatePerson(person);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public CommonRestError userNotFound(final PersonNotFoundException pnfe) {
        return new CommonRestError(4, "Username " + pnfe.getUsername() + " was not found");
    }

}
