package corona.survivor.spring.restcontroller;
import corona.survivor.spring.model.Recovery;
import corona.survivor.spring.service.RecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class RecoveryController {

    @Autowired
    RecoveryService recoveryService;

    @PostMapping("/createRecovery")
    public Recovery createRecovery(@RequestBody Recovery recoveryModel) throws InterruptedException, ExecutionException {
        return recoveryService.saveRecovery(recoveryModel);
    }
}
