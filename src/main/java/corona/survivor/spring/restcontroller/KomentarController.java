package corona.survivor.spring.restcontroller;

import corona.survivor.spring.model.DataPenerimaDonor;
import corona.survivor.spring.model.Komentar;
import corona.survivor.spring.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class KomentarController {

    @Autowired
    KomentarService komentarService;

    @PostMapping("/createKomentar")
    public Komentar postKomentar(@RequestBody Komentar komentar, @RequestParam String idArtikel) throws InterruptedException, ExecutionException {
        return komentarService.createKomentar(komentar,idArtikel);
    }
}
