package corona.survivor.spring.restcontroller;
import corona.survivor.spring.model.Calendar;
import corona.survivor.spring.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @PostMapping("/createCalendar")
    public Calendar createDataCalendar(@RequestBody Calendar calendarModel) throws InterruptedException, ExecutionException {
        return calendarService.saveCalendar(calendarModel);
    }

    @GetMapping("/calculateSembuhAwal/{nomorCalendar}")
    public String calculateSembuhAwalCalendar(@PathVariable String nomorCalendar){
        try{
            calendarService.calculateSembuhAwal(nomorCalendar);
            return "Success";
        }catch(Exception e){
            return "Error";
        }
    }
}
