package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Rent;
import alm.carrentalproject.Entity.RentTemp;
import alm.carrentalproject.Entity.Vehicle;
import alm.carrentalproject.Repository.RentTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RentTempController {

    @Autowired
    private RentTempRepository rentTempRepository;

    @GetMapping("/addTempRent")
    public ModelAndView showRentPage(){
        ModelAndView mav=new ModelAndView("addTempRent");
        Rent newRent=new Rent();
        mav.addObject("rent",newRent);

        return mav;
    }

    @PostMapping("/saveTempRent")
    public String saveRent(@Valid @ModelAttribute RentTemp rent, Errors errors, Model model, RedirectAttributes redirAttrs) {
        if (null != errors && errors.getErrorCount() > 0) {
            return"redirect:/addTempRent";
        } else {
            redirAttrs.addFlashAttribute("success", "Your rent saved successfully!!");
            rentTempRepository.save(rent);
            return"redirect:/index";
        }
    }
}
