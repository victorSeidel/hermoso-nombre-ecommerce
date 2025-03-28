package com.victor.hermoso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController 
{
    @PostMapping("/pay")
    public String processPayForm(@RequestParam String title, @RequestParam int quantity, @RequestParam float unitiPrice) 
    {
        return "resultado";
    }
}
