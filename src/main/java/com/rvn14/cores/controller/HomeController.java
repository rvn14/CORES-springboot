package com.rvn14.cores.controller;
import com.rvn14.cores.model.User;

import com.rvn14.cores.repository.UserRepository;
import com.rvn14.cores.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    //new

    private final UserService userService;
    private final UserRepository userRepository;

    public HomeController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //end

    @GetMapping({"/", ""})
    public String home() {
        return "index";
    }

    // Login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //new-Friends Directory Page
    @GetMapping("/friends")
    public String listUsers(@AuthenticationPrincipal UserDetails userDetails,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {

        Page<User> usersPage = userService.getAllUsersExcept(userDetails.getUsername(), page, size);
        User currentuser = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();


        model.addAttribute("usersPage", usersPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("user", currentuser);
        return "friends"; // => templates/friends.html
    }


}
