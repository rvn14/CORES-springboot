package com.rvn14.cores.controller;

import com.rvn14.cores.dto.PostForm;
import com.rvn14.cores.model.User;
import com.rvn14.cores.service.PostService;
import com.rvn14.cores.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {



    private final PostService postService;
    private final UserRepository userRepository;

    @GetMapping("/index")
    public String showFeed(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("posts", postService.getPostsByUser(user));
        return "index";
    }

    @PostMapping("/posts")
    public String createPost(@Valid @ModelAttribute("postForm") PostForm postForm,
                             BindingResult result,
                             @AuthenticationPrincipal UserDetails userDetails,
                             Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        if (result.hasErrors()) {
            model.addAttribute("posts", postService.getPostsByUser(user));
            return "index";
        }

        postService.createPost(postForm.getContent(), user);
        return "redirect:/index";
    }
}
