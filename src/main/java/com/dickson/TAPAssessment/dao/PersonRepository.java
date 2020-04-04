package com.dickson.TAPAssessment.dao;

import com.dickson.TAPAssessment.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
//    int insertPerson(UUID id, Person person);
//
//    default int insertPerson(Person person) {
//        UUID id = UUID.randomUUID();
//        return insertPerson(id, person);
//    }
    // @Query (SELECT h.id, h.type, p.name, p.gender, p.maritalStatus, p.occupation, p.income, p.dob, p.spouse)
    @Query("SELECT h, p " +
            "FROM Person p, Household h WHERE h.id = p.household")
    List<Object[]> getAllHouseholds();
}
