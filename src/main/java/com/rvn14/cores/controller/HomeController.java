package com.rvn14.cores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping({"/", ""})
    public String root() {
        return "redirect:/login";
    }

    // Login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Registration (Sign Up) page
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
