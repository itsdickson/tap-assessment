package com.dickson.TAPAssessment.dao;

import com.dickson.TAPAssessment.model.Household;
import com.dickson.TAPAssessment.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findById(Integer id);

    List<Person> findByDobAfter(Date date);

    List<Person> findByMaritalStatusEquals(Person.MaritalStatus maritalStatus);
}
