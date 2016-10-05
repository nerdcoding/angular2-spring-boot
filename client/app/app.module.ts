/*
 * app.module.ts
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

import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import ApplicationComponent from "./components/application/application.component";
import NavbarComponent from "./components/navbar/navbar.component";
import FooterComponent from "./components/footer/footer.component";
import PersonSearchComponent from "./components/person/person-search/person-search.component";
import PersonOverviewComponent from "./components/person/person-overview/person-overview.component";
import SettingsComponent from "./components/settings/settings.component";
import {Http404Component} from "./components/error/404/http-404.component";
import {routing} from "./app.routing";
import {HttpModule} from "@angular/http";
import {PersonService} from "./services/person.service";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        routing
    ],
    declarations: [
        ApplicationComponent,
        NavbarComponent,
        FooterComponent,
        PersonSearchComponent,
        PersonOverviewComponent,
        SettingsComponent,
        Http404Component
    ],
    providers: [
        PersonService
    ],
    bootstrap: [
        ApplicationComponent
    ]
})
export class AppModule {}