/*
 * WebSecurityConfig.java
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

package org.nerdcoding.sample.angular2.server.web;

import org.nerdcoding.sample.angular2.server.service.user.UserLoginService;
import org.nerdcoding.sample.angular2.server.web.authentication.CrossOriginResourceSharingFilter;
import org.nerdcoding.sample.angular2.server.web.authentication.Http401UnauthorizedEntryPoint;
import org.nerdcoding.sample.angular2.server.web.authentication.StatelessAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring configuration for Spring security.
 *
 * @author Tobias Koltsch
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;
    @Autowired
    protected StatelessAuthenticationFilter statelessAuthenticationFilter;
    @Autowired
    private CrossOriginResourceSharingFilter crossOriginResourceSharingFilter;
    @Autowired
    private UserLoginService userLoginService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(http401UnauthorizedEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(crossOriginResourceSharingFilter, ChannelProcessingFilter.class)
                .addFilterBefore(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService);
    }


    @Override
    @Bean // Enable autowiring of "AuthenticationManager" (e.g. LoginController)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
