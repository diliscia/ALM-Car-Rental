package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Billing;
import alm.carrentalproject.Entity.ChargeRequest;
import alm.carrentalproject.Repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckoutController {

    @Autowired
    private BillingRepository billingRepository;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @GetMapping("/checkout")
    public ModelAndView checkout(@RequestParam("billId") Long billId, ModelMap modelMap) {
        ModelAndView mav = new ModelAndView("checkout");
        modelMap.put("billId", billId);
        Billing bill = billingRepository.findById(billId).get();
        int totalAmount = (int)(bill.getAmount()+bill.getLate_fee());
        mav.addObject("amount", totalAmount * 100); // in cents
        mav.addObject("stripePublicKey", stripePublicKey);
        mav.addObject("currency", ChargeRequest.Currency.CAD);

        mav.addObject("bill",bill);
        return mav;
    }
}

