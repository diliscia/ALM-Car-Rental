package alm.carrentalproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/check")
    public String home(Model model) {
        return "checkout";
    }
}
