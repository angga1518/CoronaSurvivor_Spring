package corona.survivor.spring.restcontroller;
import corona.survivor.spring.model.Gejala;
import corona.survivor.spring.service.GejalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class GejalaController {

    @Autowired
    GejalaService gejalaService;

    @PostMapping("/createGejala")
    public Gejala createGejala(@RequestBody Gejala gejalaModel) throws InterruptedException, ExecutionException {
        return gejalaService.saveGejala(gejalaModel);
    }
}
