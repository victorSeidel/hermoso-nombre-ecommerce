package com.victor.hermoso.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="Item")

@Getter
@Setter

@AllArgsConstructor

@Component
public class Iten 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int quantity;
    private float unityPrice;
    private int categoryId;

    @OneToOne(mappedBy = "iten", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToOne(mappedBy = "iten", cascade = CascadeType.ALL)
    private Category category;

    public Iten(int id, String title, int quantity, float unityPrice, int categoryId)
    {
        id         = this.id;
        title      = this.title;
        quantity   = this.quantity;
        unityPrice = this.unityPrice;
        categoryId = this.categoryId;
    }

    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }

    public Payment getPayment()
    {
        return this.payment;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Category getCategory()
    {
        return this.category;
    }
}
