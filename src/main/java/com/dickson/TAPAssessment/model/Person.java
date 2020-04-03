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

    private int income;
    private Date dob;
    private Integer spouse;
    private Integer household;

    public Person() {}

    public Person(String name, Gender gender, MaritalStatus maritalStatus, OccupationType occupation,
                  int income, Date dob, Integer spouse, Integer household) {
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
                  int income, Date dob, Integer spouse, Integer household) {
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

    public int getIncome() {
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
}
