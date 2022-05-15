package alm.carrentalproject.Service;

import alm.carrentalproject.Entity.Billing;
import alm.carrentalproject.Repository.BillingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@AllArgsConstructor
public class BillingService {

    @Autowired
    private final BillingRepository billingRepository;

    public ModelAndView getAllBill() {
        ModelAndView mav = new ModelAndView("bill_list");
        List<Billing> list_bills = billingRepository.findAll();
        System.out.println(list_bills);
        mav.addObject("list_bills", list_bills);
        return mav;
    }


}
