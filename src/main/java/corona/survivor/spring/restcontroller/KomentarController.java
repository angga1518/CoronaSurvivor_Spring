package corona.survivor.spring.restcontroller;

import corona.survivor.spring.model.Komentar;
import corona.survivor.spring.rest.BaseResponse;
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
    public BaseResponse<Komentar> postKomentar(@RequestBody Komentar komentar, @RequestParam String idArtikel) throws InterruptedException, ExecutionException {
        Komentar komentarCreated = komentarService.createKomentar(komentar,idArtikel);
        return new BaseResponse<Komentar>(200,"Success",komentar);
    }

    @GetMapping("/getKomentarArtikel")
    public BaseResponse<List<KomentarPayload>> getAllKomentar(@RequestParam String idArtikel , @RequestParam String email){
        List<Komentar> listKomentar = new ArrayList<Komentar>();
        List<KomentarPayload> listKomentarPayload = new ArrayList<>();
        try{
            listKomentar = komentarService.getAllKomentarByIdArtikel(idArtikel);
            listKomentarPayload = komentarService.getAllKomentarWithReplies(listKomentar,email);
        }
        catch (Exception e){
            System.out.println(e);
            return new BaseResponse<List<KomentarPayload>>(400,"Error Not Found",null);
        }
        return new BaseResponse<List<KomentarPayload>>(200,"Success",listKomentarPayload);
    }

    @PostMapping("/createReply")
    public BaseResponse<Komentar>  postReply(@RequestBody Komentar komentar, @RequestParam String idParentKomentar) throws InterruptedException, ExecutionException {
        Komentar replyCreated = komentarService.createReplies(komentar,idParentKomentar);
        return new BaseResponse<Komentar>(200,"Success created reply",replyCreated);
    }

    @PostMapping("/postLikedKomentar")
    public BaseResponse<KomentarPayload> postLikedKomentar(@RequestBody KomentarPayload komentarPayload, @RequestParam String email) throws InterruptedException, ExecutionException{
        String message = komentarService.handleLikedKomentar(komentarPayload,email);
        return new BaseResponse<>(200,message,komentarPayload);
    }
}
