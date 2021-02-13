package corona.survivor.spring.restcontroller;

import corona.survivor.spring.model.Artikel;
import corona.survivor.spring.model.DataPemberiDonor;
import corona.survivor.spring.service.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ArtikelController {

    @Autowired
    ArtikelService artikelService;

    @GetMapping("/getSavedArtikel")
    public List<Artikel> getDataPemberiDonorByEmail(@RequestParam String email){
        List<Artikel> listArtikel = new ArrayList<>();
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
    public List<Artikel> getAllArtikel(){
        List<Artikel> listArtikel = new ArrayList<>();
        try{
            listArtikel = artikelService.getAllArtikel();
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return listArtikel;
    }

    @GetMapping("/getArtikelById")
    public Artikel getArtikelById(@RequestParam String idArtikel) throws InterruptedException, ExecutionException {
        return artikelService.getArtikelById(idArtikel);
    }
}
