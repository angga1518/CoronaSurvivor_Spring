package corona.survivor.spring.restcontroller;

import corona.survivor.spring.rest.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import corona.survivor.spring.model.Pengguna;
import corona.survivor.spring.service.PenggunaService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pengguna")
public class PenggunaController {

    @Autowired
    PenggunaService penggunaService;

    @PostMapping("/create")
    public BaseResponse<Pengguna> createPengguna(@RequestBody Pengguna pengguna) throws InterruptedException, ExecutionException {
        Pengguna penggunaCreated =  penggunaService.createPengguna(pengguna);
        return new BaseResponse<Pengguna>(200,"Success",penggunaCreated);
    }

    @GetMapping("/get")
    public BaseResponse<Pengguna> getPengguna(@RequestParam String email) throws InterruptedException, ExecutionException {
        Pengguna penggunaCreated =penggunaService.getPengguna(email);
        if(penggunaCreated != null){
            return new BaseResponse<Pengguna>(200,"Success",penggunaCreated);
        }else{
            return new BaseResponse<Pengguna>(400,"Not Found",penggunaCreated);
        }
    }
}