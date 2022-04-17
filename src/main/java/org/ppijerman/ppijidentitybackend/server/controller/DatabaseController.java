package org.ppijerman.ppijidentitybackend.server.controller;

import org.ppijerman.ppijidentitybackend.server.repository.ApplicationRepository;
import org.ppijerman.ppijidentitybackend.server.repository.BranchRepository;
import org.ppijerman.ppijidentitybackend.server.repository.CategoryRepository;
import org.ppijerman.ppijidentitybackend.server.repository.InstituteRepository;
import org.ppijerman.ppijidentitybackend.server.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

import org.ppijerman.ppijidentitybackend.server.dto.*;

public class DatabaseController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private BranchRepository branchRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private InstituteRepository instituteRepository;
    
    @Autowired
    private PersonRepository personRepository;

    // TODO: Implement other methods needed

    List<Application> listAllApplications() {
        return applicationRepository.findAll();
    }

    Application getApplication(UUID id) {
        return applicationRepository.getById(id);
    }

    List<Branch> listAllBranches() {
        return branchRepository.findAll();
    }

    Branch getBranch(UUID id) {
        return branchRepository.getById(id);
    }

    List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    Category getCategory(UUID id) {
        return categoryRepository.getById(id);
    }

    List<Institute> listAllInstitutes() {
        return instituteRepository.findAll();
    }

    Institute getInstitute(UUID id) {
        return instituteRepository.getById(id);
    }
     
    
    List<Person> listAllPersons() {
        return personRepository.findAll();
    }

    Person addNewPerson(Person newPerson) {
        return personRepository.save(newPerson);
    }

    Person getPerson(UUID id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFound(id));
    }

    void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }



}
