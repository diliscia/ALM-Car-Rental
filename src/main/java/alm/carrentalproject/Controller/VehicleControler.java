package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Vehicle;
import alm.carrentalproject.Repository.VehicleRepository;
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
public class VehicleControler {

    @Autowired
    private final VehicleRepository vehicleRepo;

    @GetMapping("/admin/vehicles")
    public ModelAndView vehiclesPage() {
        ModelAndView mav = new ModelAndView("vehicles");
        List<Vehicle> vehicleList = vehicleRepo.findAll();
        mav.addObject("vehicle", vehicleList);
        return mav;
    }

    @GetMapping("/admin/addVehicle")
    public ModelAndView addVehicle() {
        ModelAndView mav = new ModelAndView("add-vehicle");
        Vehicle newVehicle = new Vehicle();
        mav.addObject("vehicle", newVehicle);
        return mav;
    }

    @PostMapping("/admin/saveVehicle")
    public String saveVehicle(@Valid @ModelAttribute Vehicle vehicle, Errors errors, Model model, RedirectAttributes redirAttrs) {
        if (null != errors && errors.getErrorCount() > 0) {
            return"/admin/add-vehicle";
        } else {
            redirAttrs.addFlashAttribute("success", "Vehicle details saved successfully!!");
//            model.addAttribute("success", "Vehicle details saved successfully!!");
            vehicleRepo.save(vehicle);
            return"redirect:/admin/vehicles";
        }
    }

    @GetMapping("/admin/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long vehicleId) {
        ModelAndView mav = new ModelAndView("add-vehicle");
        Vehicle vehicle = vehicleRepo.findById(vehicleId).get();
        mav.addObject("vehicle", vehicle);
        return mav;
    }

    @GetMapping("/admin/deleteVehicle")
    public String deleteVehicle(@RequestParam Long vehicleId, Model model, RedirectAttributes redirAttrs) {
        redirAttrs.addFlashAttribute("success", "Vehicle deleted successfully!!");
//        model.addAttribute("success", "Vehicle deleted successfully!!");
        vehicleRepo.deleteById(vehicleId);
        return"redirect:/admin/vehicles";
    }
}
