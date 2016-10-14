/*
 * PersonRepository.java
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

package org.nerdcoding.sample.angular2.server.domain.repository.person;

import org.nerdcoding.sample.angular2.server.domain.entity.person.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repositroy for {@link Person} document.
 *
 * @author Tobias Koltschxtends MongoRepository<Category, String> {
 * @version 1.0.0
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    Person findByUsername(final String username);

}
