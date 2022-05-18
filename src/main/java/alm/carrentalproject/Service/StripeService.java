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

    private String secretKey = "sk_test_51Kyev5D6hSMxeUqVL4Cluwn4bewVKGD43bRftb5WFpLNabY0B0Dsm6b8P36ZLnx1Zx04i1lZJ405Vn5AHsCK9iQz00oncvVoSM";

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
