package com.dickson.TAPAssessment.dao;

import com.dickson.TAPAssessment.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
//    int insertPerson(UUID id, Person person);
//
//    default int insertPerson(Person person) {
//        UUID id = UUID.randomUUID();
//        return insertPerson(id, person);
//    }
}
