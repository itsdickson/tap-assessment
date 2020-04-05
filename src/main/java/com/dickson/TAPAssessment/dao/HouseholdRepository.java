package com.dickson.TAPAssessment.dao;

import com.dickson.TAPAssessment.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, UUID> {

    // @Query (SELECT h.id, h.type, p.name, p.gender, p.maritalStatus, p.occupation, p.income, p.dob, p.spouse)
    @Query("SELECT h, p " +
            "FROM Person p, Household h WHERE h.id = p.household")
    List<Object[]> getAllHouseholdsAndPersons();

    @Query("SELECT h, p FROM Person p, Household h WHERE h.id = p.household AND h.id = :householdId")
    List<Object[]> getAllHouseholdsAndPersonsById(@Param("householdId") Integer householdId);

}
