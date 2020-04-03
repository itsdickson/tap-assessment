package com.dickson.TAPAssessment.dao;

import com.dickson.TAPAssessment.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, UUID> {
//    int insertHousehold(UUID id, Household household);
//
//    default int insertHousehold(Household household) {
//        UUID id = UUID.randomUUID();
//        return insertHousehold(id, household);
//    }
}
