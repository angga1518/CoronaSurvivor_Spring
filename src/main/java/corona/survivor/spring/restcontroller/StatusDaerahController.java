package corona.survivor.spring.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import corona.survivor.spring.service.StatusDaerahService;

@RestController
public class StatusDaerahController {

    @Autowired
    StatusDaerahService statusDaerahService;

    @GetMapping("/test")
    public String  test(){
        return statusDaerahService.getAllProvinsi();
    }



    
}
