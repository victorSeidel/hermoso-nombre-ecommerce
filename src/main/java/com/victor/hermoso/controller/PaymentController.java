package com.victor.hermoso.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.victor.hermoso.model.Iten;
import com.victor.hermoso.model.Payment;

@Controller
public class PaymentController 
{
    @GetMapping("/paymentForm")
    public String showPaymentForm() 
    {
        return "paymentForm";
    }
    
    @PostMapping("/generatePaymentLink")
    public String generatePaymentLink(@RequestParam int itemId, @RequestParam String itemTitle, @RequestParam int itemQuantity, @RequestParam float itemPrice,  Model model) 
    {
        Iten mainIten = new Iten(itemId, itemTitle, itemQuantity, itemPrice);
    
        ArrayList<Iten> itens = new ArrayList<>();

        Payment payment = new Payment();
        String paymentLink = payment.getPaymentLink(mainIten, itens);
        
        model.addAttribute("paymentLink", paymentLink);
        
        return "paymentForm";
    }
}
