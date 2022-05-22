package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Billing;
import alm.carrentalproject.Entity.Rent;
import alm.carrentalproject.Entity.Vehicle;
import alm.carrentalproject.Repository.BillingRepository;
import alm.carrentalproject.Repository.RentRepository;
import alm.carrentalproject.Service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class BillingController {

    @Autowired
    private final BillingService billingService;

    @Autowired
    private final BillingRepository billingRepository;

    @Autowired
    private final RentRepository rentRepository;

    @GetMapping({"/admin/bill-list"})
    public ModelAndView getListOfBill() {
        return billingService.getAllBill();
    }

    @GetMapping("/admin/addBillForm")
    public ModelAndView addBill() {
        ModelAndView mav = new ModelAndView("add-bill-form");
        mav.addObject("bill",new Billing());
        List<Rent> rents = rentRepository.findAll();
        mav.addObject("rents", rents);
        return mav;
    }

    @PostMapping("/admin/saveBill")
    public String saveBill(@Valid @ModelAttribute Billing bill, BindingResult result, Model model, RedirectAttributes redirAttrs) {
        if (bill.getDue_date() != null && bill.getBill_date().after(bill.getDue_date())) {
            FieldError dueDate = new FieldError("bill", "due_date", "Due date must be after the bill date ");
            result.addError(dueDate);
        };
        if (result.hasErrors()) {
            return "add-bill-form";
        } else {
            redirAttrs.addFlashAttribute("success", "Bill details saved successfully!!");
            billingRepository.save(bill);
            return "redirect:bill_list";
        }
    }

    @GetMapping("/admin/showBill")
    public ModelAndView showUpdateBill(@RequestParam Long billId) {
        ModelAndView mav = new ModelAndView("add-bill-form");
        Billing bill = billingRepository.findById(billId).get();
        List<Rent> rents = rentRepository.findAll();
        mav.addObject("rents", rents);
        mav.addObject("bill", bill);
        return mav;
    }

}
