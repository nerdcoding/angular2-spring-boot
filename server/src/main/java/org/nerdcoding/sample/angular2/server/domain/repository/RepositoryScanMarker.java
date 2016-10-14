/*
 * RepositoryScanMarker.java
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

package org.nerdcoding.sample.angular2.server.domain.repository;

import org.springframework.data.repository.Repository;

/**
 * Marker interface. Spring uses that interface to search all {@link Repository}s in this and all sub packages.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
public interface RepositoryScanMarker {
}
