/*
 * Person.java
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

package org.nerdcoding.sample.angular2.server.domain.entity.person;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Entity represents a MongoDB collection.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@Document
public class Person {

    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    private String username;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Indexed(unique = true)
    private String eMail;

    @NotNull
    private Gender gender;

    private String street;

    private String houseNumber;

    private String zipcode;

    private String city;

    private String state;

    private String country;

    private String creditCardNumber;

    private String creditCardType;

    private String imageBase64;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(final String eMail) {
        this.eMail = eMail;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(final String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(final String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(final String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(final String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(final String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
