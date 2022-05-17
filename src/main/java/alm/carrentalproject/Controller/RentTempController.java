package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Insurance;
import alm.carrentalproject.Entity.Vehicle;
import alm.carrentalproject.Repository.InsuranceRepository;
import alm.carrentalproject.Repository.RentTempRepository;
import alm.carrentalproject.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class RentTempController {

    @Autowired
    private RentTempRepository rentTempRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;

//    @GetMapping("/addTempRent")
//    public ModelAndView showRentPage(){
//        ModelAndView mav=new ModelAndView("addTempRent");
//        Rent newRent=new Rent();
//        mav.addObject("rent",newRent);
//        return mav;
//    }

//    @PostMapping("/saveTempRent")
//    public String addTempRent(@Valid RentTemp rent, BindingResult result, Model model, Principal principal) {
//        // TODO: validation and error display
//        if (result.hasErrors()) {
//            return "addTempRent";
//        }
//        // TODO: author not found exception
//        Long rentId = rentTempRepository.save(rent).getId();
//        model.addAttribute("rentId", rentId);
//        return "redirect:/client-vehicles?rentId=" + rentId.toString();
//    }


//    @GetMapping("/client-vehicles")
//    public String selectVehicle(@RequestParam Long rentTempId, Model model, RedirectAttributes redirAttrs) {
//        redirAttrs.addFlashAttribute("success", "Vehicle selected successfully!!");
//        Vehicle vehicle = vehicleRepo.findById(vehicleId).get();
//        return"redirect:/insurances";
//    }

    @GetMapping("/getDateTime")
    public String getDateTime() {
        return "addDateTime";
    }

//    @GetMapping("/saveDateTime")                     // it only support port method
//    public String saveDetails(@RequestParam("pickup_date") String pickup_date,
//                              @RequestParam("pickup_time") String pickup_time,
//                              @RequestParam("drop_date") String drop_date,
//                              @RequestParam("drop_time") String drop_time,
//                              ModelMap modelMap) {
//        // write your code to save details
//        modelMap.put("pickup_date", pickup_date);
//        modelMap.put("pickup_time", pickup_time);
//        modelMap.put("drop_date", drop_date);
//        modelMap.put("drop_time", drop_time);
//        return "/client-vehicles";           // welcome is view name. It will call welcome.jsp
//    }

    @GetMapping("/saveDateTime")                     // it only support port method
    public ModelAndView saveDetails(@RequestParam("pickup_date") String pickup_date,
                                    @RequestParam("pickup_time") String pickup_time,
                                    @RequestParam("drop_date") String drop_date,
                                    @RequestParam("drop_time") String drop_time,
                                    ModelMap modelMap) {
        // write your code to save details
        modelMap.put("pickup_date", pickup_date);
        modelMap.put("pickup_time", pickup_time);
        modelMap.put("drop_date", drop_date);
        modelMap.put("drop_time", drop_time);
        ModelAndView mav = new ModelAndView("client-vehicles");
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        mav.addObject("vehicle", vehicleList);
        return mav;
    }

    @GetMapping("/setVehicle")
    public ModelAndView saveDetails(@RequestParam("pickup_date") String pickup_date,
                                    @RequestParam("pickup_time") String pickup_time,
                                    @RequestParam("drop_date") String drop_date,
                                    @RequestParam("drop_time") String drop_time,
                                    @RequestParam("vehicleId") Long vehicleId,
                                    ModelMap modelMap) {
        modelMap.put("pickup_date", pickup_date);
        modelMap.put("pickup_time", pickup_time);
        modelMap.put("drop_date", drop_date);
        modelMap.put("drop_time", drop_time);
        modelMap.put("vehicleId", vehicleId);
        ModelAndView mav = new ModelAndView("insurances");
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        mav.addObject("vehicle", vehicle);
        List<Insurance> insuranceList = insuranceRepository.findAll();
        mav.addObject("insurances", insuranceList);
        return mav;
    }

    @GetMapping("/setInsurance")
    public ModelAndView saveDetails(@RequestParam("pickup_date") String pickup_date,
                                    @RequestParam("pickup_time") String pickup_time,
                                    @RequestParam("drop_date") String drop_date,
                                    @RequestParam("drop_time") String drop_time,
                                    @RequestParam("vehicleId") Long vehicleId,
                                    @RequestParam("insuranceId") Long insuranceId,
                                    ModelMap modelMap) {
        modelMap.put("pickup_date", pickup_date);
        modelMap.put("pickup_time", pickup_time);
        modelMap.put("drop_date", drop_date);
        modelMap.put("drop_time", drop_time);
        modelMap.put("vehicleId", vehicleId);
        modelMap.put("insuranceId", insuranceId);
        ModelAndView mav = new ModelAndView("confirmation");
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        mav.addObject("vehicle", vehicle);
        Insurance insurance = insuranceRepository.findById(vehicleId).get();
        mav.addObject("insurance", insurance);
        return mav;
    }

//    @GetMapping("/selectVehicle")
//    public String selectVehicle(@RequestParam Long vehicleId, Model model, RedirectAttributes redirAttrs) {
//        redirAttrs.addFlashAttribute("success", "Vehicle selected successfully!!");
//        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
//        return"redirect:/insurances";
//    }

//    @PostMapping("/setVehicle")
//    public String setVehicle(@RequestParam Long vehicleId, Vehicle vehicle, BindingResult result, Principal principal) {
//        // TODO: validation and error display
//        if (result.hasErrors()) {
//            return "setVehicle";
//        }
////         TODO: author not found exception
//        return "redirect:/client-vehicles?id=" + vehicleId.toString();
//    }


}
