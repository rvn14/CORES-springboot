package com.rvn14.cores.controller;

import com.rvn14.cores.model.User;
import com.rvn14.cores.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequiredArgsConstructor
public class UserDirectoryController {

    private final UserService userService;

    @GetMapping("/friends")
    public String listUsers(@AuthenticationPrincipal UserDetails userDetails,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {
        Page<User> usersPage = userService.getAllUsersExcept(userDetails.getUsername(), page, size);

        // Debug log
        usersPage.forEach(user -> {
            System.out.println("User: " + user.getUsername() + " | " + user.getEmail());
        });

        model.addAttribute("usersPage", usersPage);
        return "friends";
    }
}
