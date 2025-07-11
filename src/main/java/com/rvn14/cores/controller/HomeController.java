package com.rvn14.cores.controller;

import com.rvn14.cores.model.User;
import com.rvn14.cores.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

    @GetMapping({"/", ""})
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    public String userList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        List<User> users = userRepository.findAll().stream()
                .filter(u -> !u.equals(currentUser))
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "friends/users";
    }
}
