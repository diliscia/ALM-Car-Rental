package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Rent;
import alm.carrentalproject.Entity.Vehicle;
import alm.carrentalproject.Repository.RentRepository;
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
public class RentController {

    @Autowired
    private RentRepository rentRepository;

    @GetMapping({"/addRent"})
    public ModelAndView showRentPage(){
        ModelAndView mav=new ModelAndView("rent");
        Rent newRent=new Rent();
        mav.addObject("rent",newRent);

        List<Vehicle> vehicles=rentRepository.availableVehicles();
        mav.addObject("vehicles", vehicles);

        return mav;
    }

    @PostMapping("/saveRent")
    public String saveRent(@Valid @ModelAttribute Rent rent, Errors errors, Model model, RedirectAttributes redirAttrs) {
        if (null != errors && errors.getErrorCount() > 0) {
            return"redirect:/addRent";
        } else {
            redirAttrs.addFlashAttribute("success", "Your rent saved successfully!!");
            rentRepository.save(rent);
            return"redirect:/index";
        }
    }

    @GetMapping("/rents")
    public ModelAndView viewRents() {
        ModelAndView mav = new ModelAndView("rents");
        List<Rent> rents = rentRepository.findAll();
        mav.addObject("rents", rents);
        return mav;
    }

    @GetMapping("/admin/updateRent")
    public ModelAndView updateRent(@RequestParam Long rentId) {
        ModelAndView mav = new ModelAndView("rent");
        Rent rent = rentRepository.findById(rentId).get();
        mav.addObject("rent", rent);
        return mav;
    }

}
