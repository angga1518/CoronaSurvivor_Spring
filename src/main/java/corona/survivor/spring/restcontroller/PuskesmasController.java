package corona.survivor.spring.restcontroller;

import corona.survivor.spring.model.Calendar;
import corona.survivor.spring.rest.BaseResponse;
import corona.survivor.spring.service.PuskesmasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import corona.survivor.spring.firebase.FirebaseInitialize;

@RestController
public class PuskesmasController {

    @Autowired
    PuskesmasService puskesmasService;

    @Autowired
    FirebaseInitialize db;

    @GetMapping("/puskesmas/validasi/{kodePuskesmas}")
    public BaseResponse<Calendar> getCalendarByEmailPengguna(@PathVariable String kodePuskesmas) {
        try {
            Boolean kodeValid = puskesmasService.validateKodePuskesmas(kodePuskesmas);
            if (!kodeValid) {
                return new BaseResponse<Calendar>(400, "Error Not Found", null);
            }
            return new BaseResponse<Calendar>(200, "Success", null);
        } catch (Exception e) {
            return null;
        }
    }
}
