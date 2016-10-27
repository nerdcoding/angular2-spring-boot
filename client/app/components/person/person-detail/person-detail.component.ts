/*
 * person-detail.component.ts
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
import {Person} from "../../../model/person";
import {Gender} from "../../../model/gender";
import {ActivatedRoute, Router} from "@angular/router";
import {PersonService} from "../../../services/person.service";
import { Location } from '@angular/common';

@Component({
    selector: 'person-detail',
    templateUrl: 'app/components/person/person-detail/person-detail.html'
})
export default class PersonDetailComponent implements OnInit {

    person: Person;

    genders: string[];

    constructor(private route: ActivatedRoute,
                private router: Router,
                private location: Location,
                private personService: PersonService) {}

    ngOnInit() {
        this.personService
            .getPersonByUsername(this.route.snapshot.params['username'])
            .then(person => this.person = person);

        let allGenders = Object.keys(Gender);
        this.genders = allGenders.slice(allGenders.length / 2);
    }

    goBack() {
        this.location.back();
    }

    onSubmit() {
        this.personService.updatePerson(this.person);
        this.router.navigate(['/overview']);
    }

}
