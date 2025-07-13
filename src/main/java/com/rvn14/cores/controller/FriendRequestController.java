package com.rvn14.cores.controller;

import com.rvn14.cores.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    @PostMapping("/send/{receiverId}")
    public String sendRequest(@PathVariable Long receiverId, @AuthenticationPrincipal UserDetails userDetails) {
        friendRequestService.sendRequest(userDetails.getUsername(), receiverId);
        return "redirect:/users";
    }

    @PostMapping("/accept/{id}")
    public String accept(@PathVariable Long id) {
        friendRequestService.acceptRequest(id);
        return "redirect:/friends/requests";
    }

    @PostMapping("/decline/{id}")
    public String decline(@PathVariable Long id) {
        friendRequestService.declineRequest(id);
        return "redirect:/friends/requests";
    }

    @GetMapping("/requests")
    public String viewIncomingRequests(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("incomingRequests", friendRequestService.getIncomingRequests(userDetails.getUsername()));
        model.addAttribute("outgoingRequests", friendRequestService.getOutgoingRequests(userDetails.getUsername()));
        return "friends/requests";
    }
}
