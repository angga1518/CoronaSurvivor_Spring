package corona.survivor.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import corona.survivor.spring.model.Pengguna;
import corona.survivor.spring.service.PenggunaService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/pengguna")
public class PenggunaController {

    @Autowired
    PenggunaService penggunaService;

    @PostMapping("/create")
    public Pengguna createPengguna(@RequestBody Pengguna pengguna) throws InterruptedException, ExecutionException {
        return penggunaService.createPengguna(pengguna);
    }

    @GetMapping("/get")
    public Pengguna getPengguna(@RequestParam String id) throws InterruptedException, ExecutionException {
        return penggunaService.getPengguna(id);
    }
}