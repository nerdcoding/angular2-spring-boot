/*
 * login.component.ts
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

import {Component} from "@angular/core";
import {Http} from "@angular/http";
import {Router} from "@angular/router";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import 'rxjs/add/operator/toPromise';

@Component({
  templateUrl: './app/components/login/login.html'
})
export class LoginComponent {

  private serverUrl = 'http://127.0.0.1:8080/';

  private form: FormGroup;

  constructor(private router: Router, private http: Http) {
      this.form = new FormGroup({
          'userName': new FormControl('', Validators.required),
          'password': new FormControl('', Validators.required)
      });
  }

  login() {
      console.log(this.form.value);
      if (this.form.valid) {
          this.http.post(this.serverUrl + '/login', { "username": this.form.value.userName, "password": this.form.value.password })
              .toPromise()
              .then(response => {
                  var jwToken = response.json().jwtToken;
                  localStorage.setItem('id_token', jwToken);

                  this.router.navigate(['overview']);
              }).catch(this.handleError);
      }
  }

  private handleError(error: any) {
      console.log(error.status);
      console.log(error.message);
  }
}