package alm.carrentalproject.Service;


import alm.carrentalproject.Entity.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

//    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey="pk_test_51Kyev5D6hSMxeUqVEyr9ANKzw6rXi8wMSANQNQm5C6qAFNHEZUP1sTZC0QWFIF8UB8z8iw0Kp6DDRmt5mwI4xWC900Vy9XGW3v";

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest chargeRequest)
            throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}

