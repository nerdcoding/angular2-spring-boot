/*
 * app.module.ts
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

import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import ApplicationComponent from "./components/application/application.component";
import NavbarComponent from "./components/navbar/navbar.component";
import FooterComponent from "./components/footer/footer.component";
import PersonOverviewComponent from "./components/person/person-overview/person-overview.component";
import SettingsComponent from "./components/settings/settings.component";
import {Http404Component} from "./components/error/404/http-404.component";
import {routing} from "./app.routing";
import {HttpModule} from "@angular/http";
import {PersonService} from "./services/person.service";
import PersonDetailComponent from "./components/person/person-detail/person-detail.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Ng2PaginationModule} from "ng2-pagination";
import {FilterPipe} from "./pipes/filter.pipe";
import {provideAuth} from "angular2-jwt";
import {LoginComponent} from "./components/login/login.component";
import {AuthGuard} from "./services/auth/auth-guard.service";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule,
        routing,
        Ng2PaginationModule
    ],
    declarations: [
        ApplicationComponent,
        NavbarComponent,
        FooterComponent,
        LoginComponent,
        PersonOverviewComponent,
        PersonDetailComponent,
        SettingsComponent,
        Http404Component,
        FilterPipe
    ],
    providers: [
        PersonService,
        AuthGuard,
        provideAuth({
            headerName: 'X-AUTH-TOKEN',
            headerPrefix: ' ',
            tokenName: 'id_token',
            tokenGetter: (() => localStorage.getItem('id_token')),
            globalHeaders: [{'Content-Type': 'application/json'}],
            noJwtError: false,
            noTokenScheme: false
        })
    ],
    bootstrap: [
        ApplicationComponent
    ]
})
export class AppModule {}