/*
 * PersonController.java
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

package org.nerdcoding.sample.angular2.server.web.rest.person;


import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;
import org.nerdcoding.sample.angular2.server.service.person.PersonService;
import org.nerdcoding.sample.angular2.server.web.exception.CommonRestError;
import org.nerdcoding.sample.angular2.server.web.exception.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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
public class PersonController {

    private Logger LOG = LoggerFactory.getLogger(PersonController.class);

    private static final String MAPPING_PREFIX = "/persons";

    @Autowired
    private PersonService personService;

    @RequestMapping(value = MAPPING_PREFIX + "/{username}", method = RequestMethod.GET)
    public Person getPersonByUsername(@PathVariable final String username) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("****************** GET request on " + MAPPING_PREFIX +" with username " + username);
        }

        final Person result = personService.findByUsername(username);
        if (result == null) {
            throw new PersonNotFoundException(username);
        }

        return result;
    }

    @RequestMapping(value = PersonController.MAPPING_PREFIX, method = RequestMethod.GET)
    public List<Person> getAllPersons() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("****************** GET request on " + MAPPING_PREFIX);
        }

        return personService.findAllPersons();
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public CommonRestError userNotFound(final PersonNotFoundException pnfe) {
        return new CommonRestError(4, "Username " + pnfe.getUsername() + " was not found");
    }

}
