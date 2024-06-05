package com.example.tickets.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tickets.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/logout")
    public String logout() {
        // 执行注销逻辑，例如使当前会话无效
        return "redirect:/login";
    }

    @GetMapping("/delete-account")
    public ModelAndView showDeleteAccountPage(HttpSession session) {
        String userEmail = (String) session.getAttribute("userEmail");
        ModelAndView modelAndView = new ModelAndView("delete-account");
        modelAndView.addObject("userEmail", userEmail);
        return modelAndView;
    }

    @PostMapping("/delete-account")
    public String deleteAccount(HttpSession session, RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("userEmail");
        userService.deleteUserByEmail(email);
        session.invalidate(); // 注销会话
        redirectAttributes.addFlashAttribute("message", "Account deleted successfully.");
        return "redirect:/login";
    }
}
