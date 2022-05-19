package alm.carrentalproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WebController {
    @GetMapping("/check")
    public String home(Model model) {
        return "checkout";
    }

    @GetMapping({"", "/index"})
    public ModelAndView viewHomePage() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}
