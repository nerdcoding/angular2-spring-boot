/*
 * person.ts
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

export class Person {

    constructor(
        public username: string,
        public firstName: string,
        public lastName: string,
        public eMail: string,
        public gender: string,
        public street: string,
        public houseNumber: string,
        public city: string,
        public state: string,
        public country: string,
        public creditCardNumber: string,
        public creditCardType: string,
        public imageBase64: string) {}

}