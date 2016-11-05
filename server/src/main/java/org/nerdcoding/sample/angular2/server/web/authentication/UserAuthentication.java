/*
 * UserAuthentication.java
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

package org.nerdcoding.sample.angular2.server.web.authentication;

import org.nerdcoding.sample.angular2.server.domain.entity.user.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Represents the authenticated user.
 *
 * @author Tobias Koltsch
 */
public class UserAuthentication extends AbstractAuthenticationToken {

    private final User user;

    public UserAuthentication(final User user) {
        super(null);
        super.setAuthenticated(true);
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }


    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public User getDetails() {
        return user;
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

}
