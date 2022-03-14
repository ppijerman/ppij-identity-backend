package org.ppijerman.ppijidentitybackend.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    String home() {
        return "index";
    }

    @GetMapping("/privacy-policy")
    String privacyPolicy() {
        return "index";
    }

    @GetMapping("/about")
    String about() {
        return "index";
    }

    @GetMapping("/logout")
    String logout() {
        return "logout";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/secret")
    String secret() {
        return "index";
    }
}
