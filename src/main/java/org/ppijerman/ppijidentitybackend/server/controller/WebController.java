package org.ppijerman.ppijidentitybackend.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/logout")
    public String logout() { return "logout"; }

    @GetMapping("/privacy-policy")
    public String privacyPolicy() { return "privacyPolicy"; }

    @GetMapping("/register")
    public String register() { return "register"; }
}
