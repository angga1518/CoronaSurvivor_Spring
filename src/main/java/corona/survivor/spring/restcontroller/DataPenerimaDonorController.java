package corona.survivor.spring.restcontroller;

import corona.survivor.spring.model.DataPenerimaDonor;
import corona.survivor.spring.service.DataPenerimaDonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class DataPenerimaDonorController {

    @Autowired
    DataPenerimaDonorService dataPenerimaDonorService;

    @PostMapping("/createDataPenerimaDonor")
    public DataPenerimaDonor createDataPenerimaDonor(@RequestBody DataPenerimaDonor dataPenerimaDonor) throws InterruptedException, ExecutionException {
        return dataPenerimaDonorService.saveDataPendonor(dataPenerimaDonor);
    }

    @GetMapping("/getDataPenerimaDonorByEmail")
    public List<DataPenerimaDonor> getDataPenerimaDonorByEmail(@RequestParam String email){
        List<DataPenerimaDonor> listDataPenerimaDonor = new ArrayList<DataPenerimaDonor>();
        try{
            listDataPenerimaDonor = dataPenerimaDonorService.getDataPenerimaDonorByEmail(email);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return listDataPenerimaDonor;
    }

    @GetMapping("/getAllDataPenerimaDonor")
    public List<DataPenerimaDonor> getAllDataPenerimaDonor(){
        List<DataPenerimaDonor> listDataPenerimaDonor = new ArrayList<DataPenerimaDonor>();
        try{
            listDataPenerimaDonor = dataPenerimaDonorService.getAllDataPenerimaDonor();
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return listDataPenerimaDonor;
    }

    @GetMapping("/getDataPenerimaDonor")
    public DataPenerimaDonor getDataPenerimaDonor(@RequestParam String idDataPenerimaDonor) throws InterruptedException, ExecutionException {
        return dataPenerimaDonorService.getDataPenerimaDonorDetails(idDataPenerimaDonor);
    }

    @DeleteMapping("/deleteDataPemberiDonort")
    public String deleteDataPenerimaDonor(@RequestParam String idDataPenerimaDonor) {
        return dataPenerimaDonorService.deleteDataPemberiDonor(idDataPenerimaDonor);
    }

}
