package alm.carrentalproject.Controller;

import alm.carrentalproject.Model.CustomerData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api")
public class StripePaymentControllerAPI {

    @RequestMapping ("/createCustomer")
    public CustomerData index(@RequestBody CustomerData data) {
        return data;
    }
}
