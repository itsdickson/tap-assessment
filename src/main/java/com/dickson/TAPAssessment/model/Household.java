package com.dickson.TAPAssessment.model;

import javax.persistence.*;

@Entity
public class Household {
    public enum HouseholdType {landed, condominium, hdb}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private HouseholdType type;

    public Household() {}

    public Household(HouseholdType type) {
        this.type = type;
    }

    public Household(Integer id, HouseholdType type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public HouseholdType getType() {
        return type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(HouseholdType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Household{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
