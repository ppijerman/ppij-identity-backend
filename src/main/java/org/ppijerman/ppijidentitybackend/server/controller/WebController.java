package org.ppijerman.ppijidentitybackend.server.controller;
import org.ppijerman.ppijidentitybackend.server.PersonRepository;
import org.ppijerman.ppijidentitybackend.server.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;



@Controller
public class WebController {

    @Autowired
    PersonRepository personRepository;

    public WebController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @GetMapping("/privacy-policy")
    public String privacyPolicy() { return "privacy-policy"; }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping("/process_register")
    public String processRegister(Person person) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        personRepository.save(person);
        return "index";
    }
}
