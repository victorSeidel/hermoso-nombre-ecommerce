package com.victor.hermoso.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;

@Data
@Entity
@Table(name="Payment")

@Component
public class Payment
{
    private static final String ACCESS_TOKEN = "TEST-8788519022554945-032819-3a38bd8516985e3594279debf95a5fa2-616355816";
    private static final String SUCCESS_URL  = "http://localhost:8080/paymentSuccess";
    private static final String FAILURE_URL  = "http://localhost:8080/paymentFailure";
    private static final String PENDING_URL  = "http://localhost:8080/paymentPending";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "iten_id", referencedColumnName = "id")
    private Iten iten;

    public String getPaymentLink(Iten iten, ArrayList<Iten> itens)
    {
        initializeMercadoPagoSDK();
        
        Preference preference = createPreferenceWithBackUrls();
        
        Item item = new Item();
        ArrayList<Item> items = new ArrayList<>();

        configureItem(item);
        addItemToPreference(preference, items, item);
        
        savePreference(preference);
        
        return preference.getInitPoint();
    }

    private void initializeMercadoPagoSDK() 
    {
        try 
        {
            MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);
        } 
        catch (MPConfException ex) 
        {
            throw new RuntimeException("Falha ao iniciar o pagamento - ", ex);
        }
    }

    private Preference createPreferenceWithBackUrls() 
    {
        Preference preference = new Preference();
        BackUrls backUrls = new BackUrls(SUCCESS_URL, FAILURE_URL, PENDING_URL);
        preference.setBackUrls(backUrls);
        return preference;
    }

    private void configureItem(Item item) 
    {
        iten.setTitle("name");
        iten.setQuantity(1);
        iten.setUnityPrice((float) 100.00);
        iten.setPayment(this);
        iten.setCategoryId(id);

        item.setId(String.valueOf(this.iten.getId()));
        item.setTitle(iten.getTitle());
        item.setQuantity(iten.getQuantity());
        item.setUnitPrice(iten.getUnityPrice());
        item.setCategoryId(String.valueOf(this.iten.getCategoryId()));
    }

    private void addItemToPreference(Preference preference, ArrayList<Item> items, Item item) 
    {
        items.add(item);
        preference.setItems(items);
    }

    private void savePreference(Preference preference) 
    {
        try 
        {
            preference.save();
        } 
        catch (MPException e) 
        {
            throw new RuntimeException("Falha ao salvar o item - ", e);
        }
    }
}
