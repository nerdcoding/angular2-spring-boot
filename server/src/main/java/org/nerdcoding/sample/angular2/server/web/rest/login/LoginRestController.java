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

package org.nerdcoding.sample.angular2.server.web.rest.login;


import org.nerdcoding.sample.angular2.server.domain.entity.user.User;
import org.nerdcoding.sample.angular2.server.web.authentication.UserAuthentication;
import org.nerdcoding.sample.angular2.server.web.authentication.jwt.JwtAuthenticationRequest;
import org.nerdcoding.sample.angular2.server.web.authentication.jwt.JwtAuthenticationResponse;
import org.nerdcoding.sample.angular2.server.web.authentication.jwt.TokenAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handles the {@link User} resource.
 *
 * @author Tobias Koltsch
 */
@RestController
public class LoginRestController {

    private Logger LOG = LoggerFactory.getLogger(LoginRestController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JwtAuthenticationResponse login(@RequestBody final JwtAuthenticationRequest authenticationRequest) {
        LOG.debug("Login request");
        final UserAuthentication authentication = performAuthentication(authenticationRequest);
        final String jwtToken = tokenAuthenticationService.createJwtTokenforAuthentication(authentication);

        return new JwtAuthenticationResponse(jwtToken);
    }

    private UserAuthentication performAuthentication(final JwtAuthenticationRequest authenticationRequest) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return new UserAuthentication((User) authentication.getPrincipal());
    }

}
