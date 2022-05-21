package alm.carrentalproject.Controller;

import alm.carrentalproject.Entity.Statistics;
import alm.carrentalproject.Repository.StatisticsRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    private RentController rentTempController;
    @Autowired
    private StatisticsRepository statisticsRepository;

    @GetMapping("/admin/statistics")
    public ModelAndView showStatistics(){
        ModelAndView mav=new ModelAndView("statistics");
        return mav;
    }

    @GetMapping("/admin/chart")
    public String showChart() {
        return "statistic";
    }

    @RequestMapping("/admin/statistic")
    @ResponseBody
    public String showStatistics1(){
        List<Statistics> statistics = statisticsRepository.getAll();
        JsonArray jsonDay = new JsonArray();
        JsonArray jsonNumberOfBookings = new JsonArray();
        JsonObject json = new JsonObject();
        statistics.forEach(data -> {
            jsonDay.add(data.getDate());
            jsonNumberOfBookings.add(data.getNumber());
        });
        json.add("date", jsonDay);
        json.add("numberOfBookings", jsonNumberOfBookings);
        return json.toString();
    }


}
