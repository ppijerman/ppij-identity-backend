package org.ppijerman.ppijidentitybackend.server.controller;

import org.ppijerman.ppijidentitybackend.server.repository.PersonRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

import org.ppijerman.ppijidentitybackend.server.dto.Person;

@RestController
public class PersonController {
    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons")
    List<Person> all() {
        return repository.findAll();
    }

    @PostMapping("/persons")
    Person newPerson(@RequestBody Person
    newPerson) {
        return repository.save(newPerson);
    }

    // Getting a single person
    // Works like getPerson, most likely called 'one' by convention
    @GetMapping("/persons/{id}")
    Person one(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow(()->new PersonNotFound(id));
    }

    @PutMapping("/persons/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable UUID id) {
        return repository.findById(id)
        .map(person -> {
            // There are still no setters necessary for this part of the methode
            // Please check again
        })
        .orElseGet(() -> {
            // We need setters to set the person's attributes
            // Or do we? This might work differently to what I expect.
        });
    }

    @DeleteMapping("/persons/{id}")
    void deletePerson(@PathVariable UUID id) {
        repository.deleteById(id);
    }
    
}
