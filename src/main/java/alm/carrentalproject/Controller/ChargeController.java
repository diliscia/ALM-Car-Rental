package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Billing;
import alm.carrentalproject.Entity.ChargeRequest;
import alm.carrentalproject.Repository.BillingRepository;
import alm.carrentalproject.Service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.PlanCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChargeController {

    @Autowired
    private StripeService paymentsService;

    @Autowired
    private BillingRepository billingRepository;

    @PostMapping("/charge")
    public String charge(@RequestParam("billId") Long billId,ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.CAD);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());

        Billing existingBill =  billingRepository.getById(billId);
        existingBill.setIsPaid(Billing.Status.PAID);
        billingRepository.save(existingBill);
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}