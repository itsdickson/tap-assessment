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
//    int insertHousehold(UUID id, Household household);
//
//    default int insertHousehold(Household household) {
//        UUID id = UUID.randomUUID();
//        return insertHousehold(id, household);
//    }

    @Query("SELECT h, p FROM Person p, Household h WHERE h.id = p.household AND h.id = :householdId")
    List<Object[]> getAllHouseholdsAndPersonsById(@Param("householdId") Integer householdId);

}
