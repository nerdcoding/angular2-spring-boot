/*
 * RootConfiguration.java
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

package org.nerdcoding.sample.angular2.server.web;

import org.nerdcoding.sample.angular2.server.domain.EmbeddedMongoConfiguration;
import org.nerdcoding.sample.angular2.server.service.ServiceConfiguration;
import org.nerdcoding.sample.angular2.server.web.rest.RestScanMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Root spring configuration for the whole Fims application.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@Configuration
@ComponentScan(basePackageClasses = RestScanMarker.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@Import({EmbeddedMongoConfiguration.class, ServiceConfiguration.class})
public class RootConfiguration {
}
