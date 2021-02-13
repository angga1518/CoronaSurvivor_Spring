package corona.survivor.spring.restcontroller;
import corona.survivor.spring.model.DataPemberiDonor;
import corona.survivor.spring.service.DataPemberiDonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class DataPemberiDonorController {

    @Autowired
    DataPemberiDonorService dataPemberiDonorService;

    @PostMapping("/createDataPemberiDonor")
    public DataPemberiDonor createDataPemberiDonor(@RequestBody DataPemberiDonor dataPemberiDonor) throws InterruptedException, ExecutionException {
        return dataPemberiDonorService.saveDataPemberiDonor(dataPemberiDonor);
    }

    @GetMapping("/getDataPemberiDonorByEmail")
    public List<DataPemberiDonor> getDataPemberiDonorByEmail(@RequestParam String email){
        List<DataPemberiDonor> listDataPemberiDonor = new ArrayList<>();
        try{
            listDataPemberiDonor = dataPemberiDonorService.getDataPemberiDonorByEmail(email);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return listDataPemberiDonor;
    }

    @GetMapping("/getAllDataPemberiDonor")
    public List<DataPemberiDonor> getAllDataPemberiDonor(){
        List<DataPemberiDonor> listDataPemberiDonor = new ArrayList<>();
        try{
            listDataPemberiDonor = dataPemberiDonorService.getAllDataPemberiDonor();
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return listDataPemberiDonor;
    }

    @GetMapping("/getDataPemberiDonor")
    public DataPemberiDonor getDataPemberiDonor(@RequestParam String idDataPemberiDonor) throws InterruptedException, ExecutionException {
        return dataPemberiDonorService.getDataPemberiDonorDetails(idDataPemberiDonor);
    }

    @DeleteMapping("/deleteDataPemberiDonor")
    public String deleteDataPemberiDonor(@RequestParam String idDataPemberiDonor) {
        return dataPemberiDonorService.deleteDataPemberiDonor(idDataPemberiDonor);
    }
}
