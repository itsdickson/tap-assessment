package com.dickson.TAPAssessment.api;

import com.dickson.TAPAssessment.dao.HouseholdRepository;
import com.dickson.TAPAssessment.dao.PersonRepository;
import com.dickson.TAPAssessment.model.Household;
import com.dickson.TAPAssessment.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return householdRepository.getAllHouseholdsAndPersons();
    }

    @GetMapping("/household/{id}")
    public List<Object[]> getHouseholdAndPersonsById(@PathVariable String id) {
        Integer householdId = Integer.parseInt(id);
        return householdRepository.getAllHouseholdsAndPersonsById(householdId);
    }

    @GetMapping("/yoloGstGrant")
    public List<Object[]> getYoloGstGrantRecepients() {
        return householdRepository.getYoloGstGrant();
    }

    // Person Methods
    @GetMapping("/person")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping("/person")
    public Person insertPerson(@RequestBody Person person) {
        Person insertedPerson = personRepository.save(person);
        if (insertedPerson.getSpouse() != null) {
            Optional<Person> spouse = personRepository.findById(insertedPerson.getSpouse());
            if (spouse.get().getSpouse() == null) {
                personRepository.save(spouse.get());
            }
        }
        return insertedPerson;
    }
}
