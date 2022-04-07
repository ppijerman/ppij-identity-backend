package org.ppijerman.ppijidentitybackend.server.controller;

import java.util.UUID;

// Do we put this class somewhere else?
public class PersonNotFound extends RuntimeException{

    PersonNotFound(UUID id) {
        super("Could not find person with the ID: " + id + " in the repository.");
    }
    
}
