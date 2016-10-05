/*
 * person.ts
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