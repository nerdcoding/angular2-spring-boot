/*
 * TokenHandler.java
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

import com.google.common.io.BaseEncoding;
import org.joda.time.DateTime;
import org.nerdcoding.sample.angular2.server.domain.entity.user.User;
import org.nerdcoding.sample.angular2.server.service.user.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;

/**
 * Handles the JSON Web Token (JWT).
 *
 * @author Tobias Koltsch
 */
@Component
public final class TokenHandler {

    private final static byte[] JWS_SECRET = BaseEncoding.base64().encode(("For instance, on the planet Earth, man had " +
            "always assumed that he was more intelligent than dolphins because he had achieved so much — the wheel, " +
            "New York, wars and so on — whilst all the dolphins had ever done was muck about in the water having a good " +
            "time. But conversely, the dolphins had always believed that they were far more intelligent than man — for " +
            "precisely the same reasons.").getBytes(StandardCharsets.UTF_8)).getBytes(StandardCharsets.UTF_8);

    @Autowired
    private UserLoginService userLoginService;

    /**
     * Try's to determine a {@link User} based on a JWT.
     *
     * @param jwtToken The JWT a user is searched for.
     * @return The found {@link User}.
     * @throws JwtException If the JWT isn't valid and was rejected.
     * @throws UsernameNotFoundException if the user wasn't found
     */
    public User parseUserFromJwtToken(final String jwtToken) {
        final String userName = Jwts.parser()
                .setSigningKey(JWS_SECRET)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();

        return userLoginService.loadUserByUsername(userName);
    }

    /**
     * Creates a new JWT for a specific {@link User}.
     *
     * @param user The user the JWT is created for.
     * @return The created JWT.
     */
    public String createTokenForUser(final User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("name", user.getFirstName() + " " + user.getLastName())
                .setExpiration(DateTime.now().plusHours(1).toDate())
                .signWith(SignatureAlgorithm.HS256, JWS_SECRET)
                .compact();
    }

}
