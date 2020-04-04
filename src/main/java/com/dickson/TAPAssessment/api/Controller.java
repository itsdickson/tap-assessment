package com.dickson.TAPAssessment.api;

import com.dickson.TAPAssessment.dao.HouseholdRepository;
import com.dickson.TAPAssessment.dao.PersonRepository;
import com.dickson.TAPAssessment.model.Household;
import com.dickson.TAPAssessment.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    HouseholdRepository householdRepository;

    @Autowired
    PersonRepository personRepository;

    // Household Methods
    @GetMapping("/household")
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @PostMapping("/household")
    public Household insertHousehold(@RequestBody Household household) {
        return householdRepository.save(household);
    }

    @GetMapping("/households")
    public List<Object[]> getAllHouseholdsAndPersons() {
        List<Object[]> householdsAndPersons = personRepository.getAllHouseholds();
        return householdsAndPersons;
    }

    // Person Methods
    @GetMapping("/person")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping("/person")
    public Person insertPerson(@RequestBody Person person) {
        System.out.println("spouseId = " + person.getSpouse());
        return personRepository.save(person);
    }
}
