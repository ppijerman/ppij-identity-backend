package org.ppijerman.ppijidentitybackend.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    String home() {
        return "index";
    }
}
