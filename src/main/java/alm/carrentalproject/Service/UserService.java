package alm.carrentalproject.Service;

import alm.carrentalproject.Entity.Insurance;
import alm.carrentalproject.Entity.User;
import alm.carrentalproject.Entity.Vehicle;
import alm.carrentalproject.Repository.InsuranceRepository;
import alm.carrentalproject.Repository.UserRepository;
import alm.carrentalproject.Repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final static String PW_ERROR = "Password must be at least 6 " +
            "characters long and must contain at least one uppercase letter, one lower case letter, and one number. It must not be longer than 100 characters.";

    @Transactional
    public String register(@Valid User user, BindingResult result) {
        if (!user.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,100}$")) {
            FieldError pwErr = new FieldError("user", "password", PW_ERROR);
            result.addError(pwErr);
        }
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if (userExists) {
            FieldError nameTaken = new FieldError("user", "username", "Username already exist!");
            result.addError(nameTaken);
        }
        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (emailExists) {
            FieldError emailExisted = new FieldError("user", "email", "Email already exist!");
            result.addError(emailExisted);
        }
        if (!user.getPassword().equals(user.getPasswordRepeat())) {
            FieldError pwRepeat = new FieldError("user", "passwordRepeat", "Please enter the same password");
            result.addError(pwRepeat);
        }
        if (result.hasErrors()) {
            return "register";
        }
        String encodePW = bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println(encodePW);
        user.setPassword(encodePW);
        userRepository.save(user);
        return "login";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public ModelAndView listUsers() {
        ModelAndView mav = new ModelAndView("listUser");
        List<User> userList = userRepository.listOfClient();
        mav.addObject("userList", userList);
        return mav;
    }

    public ModelAndView listUsersForBooking(@RequestParam("pickup_date") String pickup_date,
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
        ModelAndView mav = new ModelAndView("listUser");
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        mav.addObject("vehicle", vehicle);
        Insurance insurance = insuranceRepository.findById(insuranceId).get();
        mav.addObject("insurance", insurance);

        List<User> userList = userRepository.listOfClient();
        mav.addObject("userList", userList);
        return mav;
    }
}
