package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.User;
import alm.carrentalproject.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;


    @GetMapping("/register")
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("register");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("/registration")
    public String register(@Valid User user, BindingResult result, Principal principal) {
        return userService.register(user, result);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login_err")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
