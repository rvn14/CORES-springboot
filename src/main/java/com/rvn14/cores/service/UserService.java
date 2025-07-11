package com.rvn14.cores.service;

import com.rvn14.cores.dto.SignupForm;
import com.rvn14.cores.model.User;
import com.rvn14.cores.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Transactional
    public String registerUser(SignupForm form) {
        if (form == null) {
            return "Registration form is missing.";
        }
        if (form.getUsername() == null || form.getUsername().trim().isEmpty()) {
            return "Username is required.";
        }
        if (form.getEmail() == null || form.getEmail().trim().isEmpty()) {
            return "Email is required.";
        }
        if (form.getPassword() == null || form.getConfirmPassword() == null) {
            return "Password and confirmation are required.";
        }
        // Check if username or email already exist
        if (userRepository.findByUsername(form.getUsername()).isPresent()) {
            return "Username is already taken.";
        }
        if (userRepository.findByEmail(form.getEmail()).isPresent()) {
            return "Email is already registered.";
        }
        // Check if passwords match
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            return "Passwords do not match.";
        }


        String firstName = "";
        String lastName = "";
        String fullName = form.getFullName();
        if (fullName != null && !fullName.trim().isEmpty()) {
            String[] names = fullName.trim().split("\\s+", 2);
            firstName = names[0];
            lastName = (names.length > 1) ? names[1] : "";
        }

        // Create new User object
        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword())); // Hash password
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCreatedAt(java.time.LocalDateTime.now());

        userRepository.save(user);
        return null; // null means success
    }
}
