/*
 * StatelessAuthenticationFilter.java
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

import org.nerdcoding.sample.angular2.server.web.authentication.jwt.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Standard Servlet filter, checks if the incoming request contains a HTTP header with an JWT token. If so, we try to
 * create an Authentication (Principal) based in this token.
 *
 * @author Tobias Koltsch <tobias@koltsch.de>
 */
@Component
public class StatelessAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private TokenAuthenticationService authenticationService;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        Authentication authentication = authenticationService.getExistingAuthentication(httpRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);

        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
