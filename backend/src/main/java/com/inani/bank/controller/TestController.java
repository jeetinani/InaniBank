package com.inani.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    
    @GetMapping("/getTest")
    public String getMethodName() {
        return "Got from Backend";
    }
    
}
