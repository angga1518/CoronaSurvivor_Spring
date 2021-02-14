package corona.survivor.spring.restcontroller;

import corona.survivor.spring.model.Artikel;
import corona.survivor.spring.model.DataPenerimaDonor;
import corona.survivor.spring.model.Komentar;
import corona.survivor.spring.rest.KomentarPayload;
import corona.survivor.spring.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class KomentarController {

    @Autowired
    KomentarService komentarService;

    @PostMapping("/createKomentar")
    public Komentar postKomentar(@RequestBody Komentar komentar, @RequestParam String idArtikel) throws InterruptedException, ExecutionException {
        return komentarService.createKomentar(komentar,idArtikel);
    }

    @GetMapping("/getKomentarArtikel")
    public List<KomentarPayload> getAllKomentar(@RequestParam String idArtikel){
        List<Komentar> listKomentar = new ArrayList<Komentar>();
        List<KomentarPayload> listKomentarPayload = new ArrayList<>();
        try{
            listKomentar = komentarService.getAllKomentarByIdArtikel(idArtikel);
            listKomentarPayload = komentarService.getAllKomentarWithReplies(listKomentar);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return listKomentarPayload;
    }

    @PostMapping("/createReply")
    public Komentar postReply(@RequestBody Komentar komentar, @RequestParam String idParentKomentar) throws InterruptedException, ExecutionException {
        return komentarService.createReplies(komentar,idParentKomentar);
    }
}
