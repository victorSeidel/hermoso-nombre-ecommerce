package com.victor.hermoso.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.mercadopago.MercadoPagoConfig;

@Configuration
public class MercadoPagoConfiguration 
{
    @Value("${mercadopago.access.token}")
    private String accessToken;

    @PostConstruct
    public void init() 
    {
        if (accessToken == null || accessToken.isEmpty()) 
        {
            System.out.println("Access token do Mercado Pago não foi configurado!");
            return;
        }

        MercadoPagoConfig.setAccessToken(accessToken);
    }
}