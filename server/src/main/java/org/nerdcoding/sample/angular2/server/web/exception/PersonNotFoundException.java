/*
 * PersonNotFoundException.java
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

package org.nerdcoding.sample.angular2.server.web.exception;

import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;

/**
 * Indicates that a {@link Person} was not found.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
public class PersonNotFoundException extends RuntimeException {

    private final String username;

    public PersonNotFoundException(final String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}