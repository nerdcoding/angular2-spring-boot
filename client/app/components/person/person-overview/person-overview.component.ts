/*
 * person-overview.ts
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

import {Component, OnInit} from "@angular/core";
import {PersonService} from "../../../services/person.service";
import {Person} from "../../../model/person";

@Component({
    selector: 'person-overview',
    templateUrl: 'app/components/person/person-overview/person-overview.html',
})
export default class PersonOverviewComponent implements OnInit {

    firstNameFilter: string;
    lastNameFilter: string;

    persons: Person[];

    constructor(private personService: PersonService) { }

    ngOnInit() {
        this.personService
            .getPersons()
            .then(persons => this.persons = persons);
    }

    name: string = 'PersonOverview';

}