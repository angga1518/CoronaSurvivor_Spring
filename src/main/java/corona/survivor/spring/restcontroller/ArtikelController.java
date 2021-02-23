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
    public BaseResponse<List<ArtikelPayload>> getSavedArtikel(@RequestParam String email){
        List<ArtikelPayload> listArtikel = new ArrayList<>();
        try{
            listArtikel = artikelService.getSavedArtikel(email);
        }
        catch (Exception e){
            System.out.println(e);
            return new BaseResponse<>(400,"Artikel not found",null);
        }
        return new BaseResponse<>(200,"Success",listArtikel);
    }

    @GetMapping("/getAllArtikel")
    public BaseResponse<List<ArtikelPayload>> getAllArtikel(@RequestParam String email){
        List<ArtikelPayload> listArtikel = new ArrayList<>();
        try{
            listArtikel = artikelService.getAllArtikel(email);
        }
        catch (Exception e){
            System.out.println(e);
            return new BaseResponse<>(400,"Artikel not found",null);
        }
        return new BaseResponse<>(200,"Success",listArtikel);
    }

    @GetMapping("/getArtikelById")
    public BaseResponse<ArtikelPayload> getArtikelById(@RequestParam String idArtikel,@RequestParam String email) throws InterruptedException, ExecutionException {
        ArtikelPayload artikelPayload = artikelService.getArtikelByIdForFrontEnd(idArtikel,email);
        if(artikelPayload == null){
            return new BaseResponse<>(400,"Artikel not found",null);
        }
        return new BaseResponse<>(200,"Success",artikelPayload);
    }

    @PostMapping("/postLikedArtikel")
    public BaseResponse<ArtikelPayload> postLikedArtikel(@RequestBody ArtikelPayload artikelPayload, @RequestParam String email) throws InterruptedException, ExecutionException{
        String message = artikelService.handleLikedArtikel(artikelPayload,email);
        return new BaseResponse<>(200,message,artikelPayload);
    }

    @PostMapping("/postSavedArtikel")
    public BaseResponse<ArtikelPayload> postSavedArtikel(@RequestBody ArtikelPayload artikelPayload, @RequestParam String email) throws InterruptedException, ExecutionException{
        String message =  artikelService.handleSavedArtikel(artikelPayload,email);
        return new BaseResponse<>(200,message,artikelPayload);
    }
}
