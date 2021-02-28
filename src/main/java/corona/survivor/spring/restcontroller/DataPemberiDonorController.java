package corona.survivor.spring.restcontroller;
import corona.survivor.spring.model.DataPemberiDonor;
import corona.survivor.spring.rest.BaseResponse;
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
    public BaseResponse<DataPemberiDonor> createDataPemberiDonor(@RequestBody DataPemberiDonor dataPemberiDonor) throws InterruptedException, ExecutionException {
        DataPemberiDonor dataPemberiDonorCreated = dataPemberiDonorService.saveDataPemberiDonor(dataPemberiDonor);
        System.out.println(dataPemberiDonorCreated.getMelahirkan());
        return new BaseResponse<DataPemberiDonor>(200,"Success",dataPemberiDonor);
    }

    @GetMapping("/getDataPemberiDonorByEmail")
    public BaseResponse<List<DataPemberiDonor>> getDataPemberiDonorByEmail(@RequestParam String email){
        List<DataPemberiDonor> listDataPemberiDonor = new ArrayList<>();
        try{
            listDataPemberiDonor = dataPemberiDonorService.getDataPemberiDonorByEmail(email);
        }
        catch (Exception e){
            System.out.println(e);
            return new BaseResponse<List<DataPemberiDonor>>(400,"Error Not Found",listDataPemberiDonor);
        }
        return new BaseResponse<List<DataPemberiDonor>>(200,"Success",listDataPemberiDonor);
    }

    @GetMapping("/getAllDataPemberiDonor")
    public BaseResponse<List<DataPemberiDonor>> getAllDataPemberiDonor(){
        List<DataPemberiDonor> listDataPemberiDonor = new ArrayList<>();
        try{
            listDataPemberiDonor = dataPemberiDonorService.getAllDataPemberiDonor();
        }
        catch (Exception e){
            System.out.println(e);
            return new BaseResponse<List<DataPemberiDonor>>(400,"Error Not Found",listDataPemberiDonor);
        }
        return new BaseResponse<List<DataPemberiDonor>>(200,"Success",listDataPemberiDonor);
    }

    @GetMapping("/getDataPemberiDonor")
    public BaseResponse<DataPemberiDonor> getDataPemberiDonor(@RequestParam String idDataPemberiDonor) throws InterruptedException, ExecutionException {
        DataPemberiDonor dataPemberiDonor = dataPemberiDonorService.getDataPemberiDonorDetails(idDataPemberiDonor);
        if(dataPemberiDonor != null){
            return new BaseResponse<DataPemberiDonor>(200,"Success",dataPemberiDonor);
        }else {
            return new BaseResponse<DataPemberiDonor>(400,"Error Not Found",null);
        }
    }

    @DeleteMapping("/deleteDataPemberiDonor")
    public BaseResponse<String> deleteDataPemberiDonor(@RequestParam String idDataPemberiDonor) {
        String message = dataPemberiDonorService.deleteDataPemberiDonor(idDataPemberiDonor);
        return new BaseResponse<String>(200,"Success","Data Pemberi Donor telah berhasil dihapus");
    }
}
