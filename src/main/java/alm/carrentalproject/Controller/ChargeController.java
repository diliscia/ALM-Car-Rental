package alm.carrentalproject.Controller;

import alm.carrentalproject.Service.ChargeProcessService;
import alm.carrentalproject.dto.ChargeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ChargeController {

    @Autowired
    private ChargeProcessService chargeProcessService;

    @PostMapping("/charge")
    public ModelAndView charge(ChargeRequest chargeRequest, Model model){

    }
}
