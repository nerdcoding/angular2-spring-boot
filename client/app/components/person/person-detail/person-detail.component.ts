/*
 * person-detail.component.ts
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
