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

    @DeleteMapping("/household/{id}")
    public Household deleteHousehold(@PathVariable String id) {
        Integer householdId = Integer.parseInt(id);
        Optional<Household> householdToDelete = householdRepository.findById(householdId);
        householdRepository.delete(householdToDelete.get());
        return householdToDelete.get();
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
                spouse.get().setMaritalStatus(Person.MaritalStatus.married);
                personRepository.save(spouse.get());
            }
        }
        return insertedPerson;
    }

    @DeleteMapping("/person/{id}")
    public Person deletePerson(@PathVariable String id) {
        Integer personId = Integer.parseInt(id);
        Optional<Person> personToDelete = personRepository.findById(personId);
        if (personToDelete.get().getSpouse() != null) {
            Optional<Person> spouse = personRepository.findById(personToDelete.get().getSpouse());
            if (spouse.get().getSpouse() != null) {
                spouse.get().setSpouse(null);
                spouse.get().setMaritalStatus(Person.MaritalStatus.divorced);
                personRepository.save(spouse.get());
            }
        }
        personRepository.delete(personToDelete.get());
        return personToDelete.get();
    }

    // Scheme Methods
    @GetMapping("/studentEncouragementBonus")
    public List<List<Object[]>> getStudentEncouragementBonusRecepients() {
        LocalDate age = LocalDate.now().minusYears(16);
        List<Person> personsOfAgeLessThan16 = personRepository.findByDobAfter(Date.valueOf(age));
        Set<Integer> set1 = new HashSet<>();
        for (Person p : personsOfAgeLessThan16) {
            set1.add(p.getHousehold());
        }

        List<Object[]> householdsWithIncomeLessThan150000 =
                householdRepository.getAllHouseholdsWithIncomeLowerThan(Long.valueOf(150000));
        Set<Integer> set2 = new HashSet<>();
        for (Object[] o : householdsWithIncomeLessThan150000) {
            set2.add(((Household)o[0]).getId());
        }

        Set<Integer> householdSet = new HashSet<>();

        for (Integer s : set1) {
            if (set2.contains(s)) {
                householdSet.add(s);
            }
        }

        List<List<Object[]>> households = new ArrayList<>();

        for (Integer i : householdSet) {
            households.add(householdRepository.getAllHouseholdsAndPersonsById(i));
        }

        return households;
    }

    @GetMapping("/familyTogethernessScheme")
    public List<List<Object[]>> getFamilyTogethernessSchemeRecepients() {
        LocalDate age = LocalDate.now().minusYears(18);
        List<Person> personsOfAgeLessThan18 = personRepository.findByDobAfter(Date.valueOf(age));
        Set<Integer> set1 = new HashSet<>();
        for (Person p : personsOfAgeLessThan18) {
            set1.add(p.getHousehold());
        }

        List<Person> marriedPersons =
                personRepository.findByMaritalStatusEquals(Person.MaritalStatus.married);
        Set<Integer> set2 = new HashSet<>();
        for (Person p : marriedPersons) {
            set2.add(p.getHousehold());
        }

        Set<Integer> householdSet = new HashSet<>();

        for (Integer s : set1) {
            if (set2.contains(s)) {
                householdSet.add(s);
            }
        }

        List<List<Object[]>> households = new ArrayList<>();

        for (Integer i : householdSet) {
            households.add(householdRepository.getAllHouseholdsAndPersonsById(i));
        }

        return households;
    }

    @GetMapping("/elderBonus")
    public List<List<Object[]>> getElderBonusRecipients() {
        LocalDate age = LocalDate.now().minusYears(50);
        List<Object[]> personsOfAgeMoreThan50 = householdRepository.getElderBonusRecipients(Date.valueOf(age));
        Set<Integer> set = new HashSet<>();
        for (Object[] o : personsOfAgeMoreThan50) {
            set.add(((Household) o[0]).getId());
        }

        List<List<Object[]>> households = new ArrayList<>();

        for (Integer i : set) {
            households.add(householdRepository.getAllHouseholdsAndPersonsById(i));
        }

        return households;
    }

    @GetMapping("/babySunshineGrant")
    public List<List<Object[]>> getBabySunshineGrantRecipients() {
        LocalDate age = LocalDate.now().minusYears(5);
        List<Person> personsOfAgeLessThan5 = personRepository.findByDobAfter(Date.valueOf(age));
        Set<Integer> set = new HashSet<>();
        for (Person p : personsOfAgeLessThan5) {
            set.add(p.getHousehold());
        }

        List<List<Object[]>> households = new ArrayList<>();

        for (Integer i : set) {
            households.add(householdRepository.getAllHouseholdsAndPersonsById(i));
        }

        return households;
    }

    @GetMapping("/yoloGstGrant")
    public List<List<Object[]>> getYoloGstGrantRecipients() {
        List<Object[]> householdsOfIncomeLessThan100k = householdRepository.getYoloGstGrantRecipients();
        Set<Integer> set = new HashSet<>();
        for (Object[] o : householdsOfIncomeLessThan100k) {
            set.add(((Household)o[0]).getId());
        }

        List<List<Object[]>> households = new ArrayList<>();

        for (Integer i : set) {
            households.add(householdRepository.getAllHouseholdsAndPersonsById(i));
        }
        return households;
    }
}
