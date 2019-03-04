/*
 * Copyright (c) 2018.
 */

package edu.njit.cs684.electronichealthrecords.domain.restmodel;

/**
 * @author sulekha
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Name;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameRest {

    private String firstName;
    private String middleName;
    private String lastName;

    public NameRest() {
    }

    public static NameRest nameToNameRestConverter(Name name) {

        NameRest nameRest = new NameRest();
        nameRest.setFirstName(name.getFirstName());
        nameRest.setMiddleName(name.getMiddleName());
        nameRest.setLastName(name.getLastName());

        return nameRest;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Name nameRestToNameConverter() {

        Name name = new Name();
        name.setFirstName(this.getFirstName());
        name.setMiddleName(this.getMiddleName());
        name.setLastName(this.getLastName());

        return name;
    }
}
