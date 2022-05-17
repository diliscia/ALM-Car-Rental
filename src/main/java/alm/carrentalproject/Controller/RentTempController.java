package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.*;
import alm.carrentalproject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class RentTempController {

    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillingRepository billingRepository;

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
        Insurance insurance = insuranceRepository.findById(insuranceId).get();
        mav.addObject("insurance", insurance);
        return mav;
    }

//    @GetMapping("/selectVehicle")
//    public String selectVehicle(@RequestParam Long vehicleId, Model model, RedirectAttributes redirAttrs) {
//        redirAttrs.addFlashAttribute("success", "Vehicle selected successfully!!");
//        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
//        return"redirect:/insurances";
//    }

    @PostMapping("/createBooking")
    public ModelAndView createBooking(@RequestParam("pickup_date") String pickup_date,
                                @RequestParam("pickup_time") String pickup_time,
                                @RequestParam("drop_date") String drop_date,
                                @RequestParam("drop_time") String drop_time,
                                @RequestParam("vehicleId") Long vehicleId,
                                @RequestParam("insuranceId") Long insuranceId,
                                ModelMap modelMap, Principal principal) throws ParseException {
        ModelAndView mav = new ModelAndView("success_booking");
        Rent newRent = new Rent();
        newRent.setPickup_date(new SimpleDateFormat("yyyy-MM-dd").parse(pickup_date));
        newRent.setPickup_time(new SimpleDateFormat("HH:mm").parse(pickup_time));
        newRent.setDrop_date(new SimpleDateFormat("yyyy-MM-dd").parse(drop_date));
        newRent.setDrop_time(new SimpleDateFormat("HH:mm").parse(drop_time));
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        newRent.setVehicle(vehicle);
        Insurance insurance = insuranceRepository.findById(insuranceId).get();
        newRent.setInsurance(insurance);
        newRent.setStatus(Rent.RENT_STATUS.PENDING);
        User user = userRepository.findByUsername(principal.getName()).get();
        newRent.setUser(user);
        rentRepository.save(newRent);
        mav.addObject("rentId", newRent.getId());
        mav.addObject("rent", newRent);
        double vehicleCostPerDay = vehicleRepository.findById(vehicleId).get().getCostPerDay();
        double insuranceCostPerDay = insuranceRepository.findById(insuranceId).get().getCostPerDay();
        long diffDays = (newRent.getDrop_date().getTime() - newRent.getPickup_date().getTime());
        diffDays = TimeUnit.DAYS.convert(diffDays, TimeUnit.MILLISECONDS) + 1;
        double totalCost = diffDays * (vehicleCostPerDay + insuranceCostPerDay);

        //long miliseconds = System.currentTimeMillis();
        //Date todayDate = new Date(miliseconds);
        Date todayDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(todayDate);
        c.add(Calendar.DAY_OF_YEAR, 90);
        System.out.println(c.getTime());
        Date dueDate = c.getTime();

        Billing bill = new Billing();
        bill.setAmount(totalCost);
        bill.setRent(rentRepository.findById(newRent.getId()).get());
        bill.setBill_date(todayDate);
        bill.setDue_date(dueDate);
        bill.setIsPaid(Billing.Status.NOT_PAID);
        bill.setLate_fee(0);
        billingRepository.save(bill);
        mav.addObject("bill", bill);

        return mav;
    }


    @GetMapping("/makepayment")
    public ModelAndView MakePayment(@RequestParam("billId") Long billId, ModelMap modelMap) {
        ModelAndView mav = new ModelAndView("payment");
        modelMap.put("billId", billId);
        Billing bill = billingRepository.findById(billId).get();
        mav.addObject("bill",bill);
        return mav;
    }



}
