package corona.survivor.spring.restcontroller;

import corona.survivor.spring.model.DataPenerimaDonor;
import corona.survivor.spring.rest.BaseResponse;
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
    public BaseResponse<DataPenerimaDonor> createDataPenerimaDonor(@RequestBody DataPenerimaDonor dataPenerimaDonor) throws InterruptedException, ExecutionException {
        DataPenerimaDonor dataPenerimaDonorCreated = dataPenerimaDonorService.saveDataPendonor(dataPenerimaDonor);
        return new BaseResponse<DataPenerimaDonor>(200,"Success",dataPenerimaDonor);
    }

    @GetMapping("/getDataPenerimaDonorByEmail")
    public BaseResponse<List<DataPenerimaDonor>> getDataPenerimaDonorByEmail(@RequestParam String email){
        List<DataPenerimaDonor> listDataPenerimaDonor = new ArrayList<DataPenerimaDonor>();
        try{
            listDataPenerimaDonor = dataPenerimaDonorService.getDataPenerimaDonorByEmail(email);
        }
        catch (Exception e){
            System.out.println(e);
            return new BaseResponse<List<DataPenerimaDonor>>(400,"Error Not Found",listDataPenerimaDonor);
        }
        return new BaseResponse<List<DataPenerimaDonor>>(200,"Success",listDataPenerimaDonor);
    }

    @GetMapping("/getAllDataPenerimaDonor")
    public BaseResponse<List<DataPenerimaDonor>> getAllDataPenerimaDonor(){
        List<DataPenerimaDonor> listDataPenerimaDonor = new ArrayList<DataPenerimaDonor>();
        try{
            listDataPenerimaDonor = dataPenerimaDonorService.getAllDataPenerimaDonor();
        }
        catch (Exception e){
            System.out.println(e);
            return new BaseResponse<List<DataPenerimaDonor>>(400,"Error Not Found",null);
        }
        return new BaseResponse<List<DataPenerimaDonor>>(200,"Success",listDataPenerimaDonor);
    }

    @GetMapping("/getDataPenerimaDonor")
    public BaseResponse<DataPenerimaDonor> getDataPenerimaDonor(@RequestParam String idDataPenerimaDonor) throws InterruptedException, ExecutionException {
        DataPenerimaDonor dataPenerimaDonor = dataPenerimaDonorService.getDataPenerimaDonorDetails(idDataPenerimaDonor);
        if(dataPenerimaDonor == null){
            return new BaseResponse<DataPenerimaDonor>(400,"Error Not Found",null);
        }else {
            return new BaseResponse<DataPenerimaDonor>(200,"Success",dataPenerimaDonor);
        }
    }

    @DeleteMapping("/deleteDataPenerimaDonor")
    public BaseResponse<String>deleteDataPenerimaDonor(@RequestParam String idDataPenerimaDonor) {
        String message = dataPenerimaDonorService.deleteDataPemberiDonor(idDataPenerimaDonor);
        return new BaseResponse<String>(200,"Success","Data Penerima Donor telah berhasil dihapus");
    }

}
