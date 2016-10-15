/*
 * CrossOriginResourceSharingFilter.java
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

package org.nerdcoding.sample.angular2.server.web.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet filter responsible to handle Cross-origin resource sharing (CORS), because our client is in a different domain.
 * <p/>
 * If the browser based Javascript makes an cross domain request, that means requests resources from a server and the
 * domain name of the server is different than the clients domain name, the Browser implicitly makes an OPTIONS request
 * to the server. Only when the Server sends Access-Control-* header which explicitly allows the client to make it's
 * resource request, the Browser makes the actual request.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@Component
public class CrossOriginResourceSharingFilter extends GenericFilterBean {

    private static final String CLIENT_URL = "http://127.0.0.1:3000";

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getHeader("ORIGIN") != null
                && originEqualsClientUrl(request.getHeader("ORIGIN"), CLIENT_URL)) {
            setAccessControlHeader((HttpServletResponse) servletResponse, request.getHeader("ORIGIN"));
        }

        if (!request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void setAccessControlHeader(final HttpServletResponse response, final String origin) {
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Origin, Accept, Access-Control-Allow-Headers, Access-Control-Request-Headers");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    boolean originEqualsClientUrl(final String origin, final String clientUrl) {
        String adjustedOrigin = switchLocalhost(origin);
        if (adjustedOrigin.endsWith("/")) {
            adjustedOrigin = adjustedOrigin.substring(0, adjustedOrigin.length()-1);
        }

        return clientUrl.equalsIgnoreCase(adjustedOrigin);
    }

    String switchLocalhost(final String origin) {
        String result = origin;
        if (origin.toLowerCase().contains("localhost")) {
            // (?i) means: replaceAllIgnoreCase
            result = origin.replaceAll("(?i)localhost", "127.0.0.1");
        }

        return result;
    }
}
