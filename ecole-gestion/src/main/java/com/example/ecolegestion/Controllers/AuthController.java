package com.example.ecolegestion.Controllers;

import com.example.ecolegestion.Entities.Utilisateur;
import com.example.ecolegestion.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login"; // Ensure you have a login.html in your templates folde

    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Utilisateur utilisateur) {
        userService.registerUser(utilisateur);
        return "redirect:/login";
    }
}
