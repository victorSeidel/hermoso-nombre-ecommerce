package com.victor.hermoso.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="Item")

@Getter
@Setter

@Component
public class Iten 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int quantity;
    private float unityPrice;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private Payment payment;

    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }

    public Payment getPayment()
    {
        return this.payment;
    }
}
