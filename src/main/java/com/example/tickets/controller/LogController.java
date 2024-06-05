package com.example.tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.tickets.service.LogService;

@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/logs")
    public ModelAndView showLogs() {
        ModelAndView modelAndView = new ModelAndView("logs");
        modelAndView.addObject("logs", logService.getAllLogs());
        return modelAndView;
    }
}
