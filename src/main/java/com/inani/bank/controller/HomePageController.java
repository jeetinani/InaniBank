package com.inani.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String index() {
        // ModelAndView mav=new ModelAndView("index");
        return "index";// mav;
    }
}