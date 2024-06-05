package com.example.tickets.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tickets.model.User;
import com.example.tickets.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes, HttpSession session) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            session.setAttribute("userEmail", email); 
            return "redirect:/search";
        } else {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationPage() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes redirectAttributes) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/register";
        }
        userService.insertUser(user);
        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public ModelAndView showForgotPasswordPage() {
        return new ModelAndView("forgot-password");
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, @RequestParam String newPassword, RedirectAttributes redirectAttributes) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            userService.updateUserPassword(email, newPassword);
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/forgot-password";
        }
    }

    @GetMapping("/all-users")
    public ModelAndView showAllUsers() {
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("all-users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
