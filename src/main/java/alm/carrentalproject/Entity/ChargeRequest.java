package alm.carrentalproject.Entity;

import lombok.Data;

@Data
public class ChargeRequest {

    public enum Currency {
        CAD, USD;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}