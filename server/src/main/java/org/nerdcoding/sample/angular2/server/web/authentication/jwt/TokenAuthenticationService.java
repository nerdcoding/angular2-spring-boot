/*
 * TokenAuthenticationService.java
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

package org.nerdcoding.sample.angular2.server.web.authentication.jwt;

import org.nerdcoding.sample.angular2.server.domain.entity.user.User;
import org.nerdcoding.sample.angular2.server.web.authentication.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;

import javax.servlet.http.HttpServletRequest;

/**
 * Initiate the creation of an JSON Web Token (JWT).
 *
 * @author Tobias Koltsch
 */
@Service
public class TokenAuthenticationService {

    public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private TokenHandler tokenHandler;

    public String createJwtTokenforAuthentication(final UserAuthentication authentication) {
        return tokenHandler.createTokenForUser(authentication.getDetails());
    }

    public Authentication getExistingAuthentication(final HttpServletRequest request) {
        final String jwtToken = request.getHeader(AUTH_HEADER_NAME);
        if (jwtToken != null) {
            try {
                final User user = tokenHandler.parseUserFromJwtToken(jwtToken);
                return new UserAuthentication(user);
            } catch (final UsernameNotFoundException | JwtException e) {
                return null;
            }
        }

        return null;
    }

}
