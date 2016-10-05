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
import PersonSearchComponent from "./components/person-search/person-search.component";
import PersonOverviewComponent from "./components/person-overview/person-overview";

@NgModule({
    imports: [
        BrowserModule
    ],
    declarations: [
        ApplicationComponent,
        NavbarComponent,
        FooterComponent,
        PersonSearchComponent,
        PersonOverviewComponent
    ],
    providers: [

    ],
    bootstrap: [
        ApplicationComponent
    ]
})
export class AppModule {}