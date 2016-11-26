/*
 * person.service.ts
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

import {Headers} from "@angular/http";
import {Person} from "../model/person";

import 'rxjs/add/operator/toPromise';
import {Injectable} from "@angular/core";
import {AuthHttp} from "angular2-jwt";

@Injectable()
export class PersonService {

    private url = 'http://127.0.0.1:8080/persons';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(public authHttp: AuthHttp) {}

    getPersons(): Promise<Person[]> {
        return this.authHttp.get(this.url)
            .toPromise()
            .then(response => response.json() as Person[])
            .catch(this.handleError);
    }

    getPersonByUsername(username: string): Promise<Person> {
        return this.authHttp.get(this.url + '/' + username)
            .toPromise()
            .then(response => response.json() as Person)
            .catch(this.handleError);
    }

    updatePerson(person: Person) {
        return this.authHttp.put(this.url, JSON.stringify(person), {headers: this.headers})
            .toPromise()
            .then(() => person)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

}