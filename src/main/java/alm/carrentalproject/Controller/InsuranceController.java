package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Insurance;
import alm.carrentalproject.Entity.Vehicle;
import alm.carrentalproject.Repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class InsuranceController {

    @Autowired
    private final InsuranceRepository insuranceRepo;

    @GetMapping("/insurances")
    public ModelAndView insurancesPage() {
        ModelAndView mav = new ModelAndView("insurances");
        List<Insurance> insuranceList = insuranceRepo.findAll();
        mav.addObject("insurances", insuranceList);
        return mav;
    }

    @GetMapping("/selectInsurance")
    public String selectInsurance(@RequestParam Long insuranceId, Model model, RedirectAttributes redirAttrs) {
        redirAttrs.addFlashAttribute("success", "Insurance selected successfully!!");
        Insurance insurance = insuranceRepo.findById(insuranceId).get();
        return"redirect:/preview";
    }


}
