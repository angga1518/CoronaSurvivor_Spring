package corona.survivor.spring.restcontroller;

import corona.survivor.spring.rest.ArtikelPayload;
import corona.survivor.spring.rest.BaseResponse;
import corona.survivor.spring.service.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ArtikelController {

    @Autowired
    ArtikelService artikelService;

    @GetMapping("/getSavedArtikel")
    public List<ArtikelPayload> getDataPemberiDonorByEmail(@RequestParam String email){
        List<ArtikelPayload> listArtikel = new ArrayList<>();
        try{
            listArtikel = artikelService.getSavedArtikel(email);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return listArtikel;
    }

    @GetMapping("/getAllArtikel")
    public List<ArtikelPayload> getAllArtikel(@RequestParam String email){
        List<ArtikelPayload> listArtikel = new ArrayList<>();
        try{
            listArtikel = artikelService.getAllArtikel(email);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return listArtikel;
    }

    @GetMapping("/getArtikelById")
    public ArtikelPayload getArtikelById(@RequestParam String idArtikel,@RequestParam String email) throws InterruptedException, ExecutionException {
        return artikelService.getArtikelByIdForFrontEnd(idArtikel,email);
    }

    @PostMapping("/postLikedArtikel")
    public BaseResponse<ArtikelPayload> postLikedArtikel(@RequestBody ArtikelPayload artikelPayload, @RequestParam String email) throws InterruptedException, ExecutionException{
        return artikelService.handleLikedArtikel(artikelPayload,email);
    }

    @PostMapping("/postSavedArtikel")
    public BaseResponse<ArtikelPayload> postSavedArtikel(@RequestBody ArtikelPayload artikelPayload, @RequestParam String email) throws InterruptedException, ExecutionException{
        return artikelService.handleSavedArtikel(artikelPayload,email);
    }
}
