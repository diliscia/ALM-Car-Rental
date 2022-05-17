package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Billing;
import alm.carrentalproject.Service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class BillingController {

    @Autowired
    private final BillingService billingService;

    @GetMapping({"/admin/bill-list"})
    public ModelAndView getListOfBill() {
        return billingService.getAllBill();
    }


}
