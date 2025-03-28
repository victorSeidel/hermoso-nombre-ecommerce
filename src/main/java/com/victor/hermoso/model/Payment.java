package com.victor.hermoso.model;

import java.util.ArrayList;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;

public class Payment
{
    public String getPaymentLink(Item item, ArrayList<Item> items)
    {
        try 
        {
            MercadoPago.SDK.setAccessToken("SEU_ACCESS_TOKEN_AQUI");
        } 
        catch (MPConfException ex) 
        {

        }

        Preference preference = new Preference();

        BackUrls backUrls = new BackUrls
        (
            "https://www.yoursite.com/paymentSuccess",
            "https://www.yoursite.com/paymentFailure",
            "https://www.yoursite.com/paymentPending"
        );

        preference.setBackUrls(backUrls);

        item.setId("");
        item.setTitle("name");
        item.setQuantity(1);
        item.setUnitPrice((float) 100.00);

        items.add(item);

        preference.setItems(items);

        try 
        {
            preference.save();
        } 
        catch (MPException e) 
        {

        }

        return preference.getInitPoint();
    }
}
