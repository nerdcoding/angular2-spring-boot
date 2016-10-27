/*
 * person.service.ts
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

import {Http, Headers} from "@angular/http";
import {Person} from "../model/person";

import 'rxjs/add/operator/toPromise';
import {Injectable} from "@angular/core";

@Injectable()
export class PersonService {

    private url = 'http://127.0.0.1:8080/persons';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) {}

    getPersons(): Promise<Person[]> {
        return this.http.get(this.url)
            .toPromise()
            .then(response => response.json() as Person[])
            .catch(this.handleError);
    }

    getPersonByUsername(username: string): Promise<Person> {
        return this.http.get(this.url + '/' + username)
            .toPromise()
            .then(response => response.json() as Person)
            .catch(this.handleError);
    }

    updatePerson(person: Person) {
        return this.http.put(this.url, JSON.stringify(person), {headers: this.headers})
            .toPromise()
            .then(() => person)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

}