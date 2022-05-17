package alm.carrentalproject.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CheckOutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    // http://localhost:8080/checkout?userId=1&&amount=40
    @GetMapping("/checkout")
    public ModelAndView checkOut(Model model, @RequestParam(required = true) String userId,
                                        @RequestParam(required = true) int amount) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("amount", amount*100);
        mav.addObject("stripePublicKey", stripePublicKey);
        mav.addObject("currency", "CAD");
        mav.addObject("userID", userId);

        return mav;
    }
}
