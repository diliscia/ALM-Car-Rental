package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Statistics;
import alm.carrentalproject.Repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    private RentTempController rentTempController;
    @Autowired
    private StatisticsRepository statisticsRepository;

    @GetMapping("/statistics")
    public ModelAndView showStatistics(){
        ModelAndView mav=new ModelAndView("statistics");

        return mav;
    }

    @GetMapping("/statistics1")
    public ModelAndView showStatistics1(){
        ModelAndView mav=new ModelAndView("statistics1");
        List<Statistics> statistics=statisticsRepository.getAll();
        mav.addObject("statistics",statistics);
        return mav;
    }
}
