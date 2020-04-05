package com.dickson.TAPAssessment.api;

import com.dickson.TAPAssessment.dao.HouseholdRepository;
import com.dickson.TAPAssessment.dao.PersonRepository;
import com.dickson.TAPAssessment.model.Household;
import com.dickson.TAPAssessment.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

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

    @GetMapping("/babySunshineGrant")
    public List<List<Object[]>> getBabySunshineGrantRecepients() {
        LocalDate age = LocalDate.now().minusYears(5);
        System.out.println("age: " + age.toString());
        List<Person> personsOfAgeLessThan5 = personRepository.findByDobAfter(Date.valueOf(age));
        Set<Integer> set = new HashSet<>();
        for (Person p : personsOfAgeLessThan5) {
            set.add(p.getHousehold());
        }

        List<List<Object[]>> res = new ArrayList<>();

        for (Integer i : set) {
            res.add(householdRepository.getAllHouseholdsAndPersonsById(i));
        }

        return res;
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
                spouse.get().setSpouse(insertedPerson.getId());
                personRepository.save(spouse.get());
            }
        }
        return insertedPerson;
    }
}
