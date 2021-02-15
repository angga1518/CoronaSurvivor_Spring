package corona.survivor.spring.model;

import java.util.List;
import org.springframework.stereotype.Component;

@Component()
public class Gejala {
    
    private int nomorGejala;
    private String namaGejala;
    private String emailPengguna;
    private List<Record> listRecord;

    public Gejala(int nomorGejala, String namaGejala, String emailPengguna, List<Record> listRecord){
        this.nomorGejala = nomorGejala;
        this.namaGejala = namaGejala;
        this.emailPengguna = emailPengguna;
        this.listRecord = listRecord;
    }

    public Gejala(){
        super();
    }
}
