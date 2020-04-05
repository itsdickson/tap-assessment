package com.dickson.TAPAssessment.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Person {
    public enum Gender {male, female}
    public enum MaritalStatus {single, married, divorced, separated, widowed}
    public enum OccupationType {unemployed, student, employed}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    private OccupationType occupation;

    private Integer income;
    private Date dob;
    private Integer spouse;
    private Integer household;

    public Person() {}

    public Person(String name, Gender gender, MaritalStatus maritalStatus, OccupationType occupation,
                  Integer income, Date dob, Integer spouse, Integer household) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.income = income;
        this.dob = dob;
        this.spouse = spouse;
        this.household = household;
    }

    public Person(Integer id, String name, Gender gender, MaritalStatus maritalStatus, OccupationType occupation,
                  Integer income, Date dob, Integer spouse, Integer household) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.income = income;
        this.dob = dob;
        this.spouse = spouse;
        this.household = household;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public OccupationType getOccupation() {
        return occupation;
    }

    public Integer getIncome() {
        return income;
    }

    public Date getDob() {
        return dob;
    }

    public Integer getSpouse() {
        return spouse;
    }

    public Integer getHousehold() {
        return household;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setOccupation(OccupationType occupation) {
        this.occupation = occupation;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setSpouse(Integer spouse) {
        this.spouse = spouse;
    }

    public void setHousehold(Integer household) {
        this.household = household;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", maritalStatus=" + maritalStatus +
                ", occupation=" + occupation +
                ", income=" + income +
                ", dob=" + dob +
                ", spouse=" + spouse +
                ", household=" + household +
                '}';
    }
}
