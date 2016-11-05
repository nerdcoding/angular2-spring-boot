/*
 * app.routing.ts
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

import {Routes, RouterModule} from "@angular/router";
import ApplicationComponent from "./components/application/application.component";
import PersonOverviewComponent from "./components/person/person-overview/person-overview.component";
import SettingsComponent from "./components/settings/settings.component";
import {Http404Component} from "./components/error/404/http-404.component";
import PersonDetailComponent from "./components/person/person-detail/person-detail.component";

const routes: Routes = [
    {
        path: '',
        redirectTo: '/overview',
        pathMatch: 'full'
    },
    {
        path: 'overview',
        component: PersonOverviewComponent
    },
    {
        path: 'details/:username',
        component: PersonDetailComponent
    },
    {
        path: 'settings',
        component: SettingsComponent
    },
    {
        path: '**',
        component: Http404Component
    }
];
export const routing = RouterModule.forRoot(routes);