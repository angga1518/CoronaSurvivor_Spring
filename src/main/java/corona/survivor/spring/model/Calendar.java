package corona.survivor.spring.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component()
public class Calendar {

    private String nomorKalender;
    private String kodePuskesmas;
    private String emailPengguna;

    private List<Gejala> listGejala;

    public List<Gejala> getListGejala() {
        return this.listGejala;
    }
    public void setListGejala(List<Gejala> listGejala) {
        this.listGejala = listGejala;
    }


    public String getNomorKalender() {
    	return this.nomorKalender;
    }
    
    public void setNomorKalender(String nomorKalender) {
    	this.nomorKalender = nomorKalender;
    }


    public String getKodePuskesmas() {
    	return this.kodePuskesmas;
    }
    public void setKodePuskesmas(String kodePuskesmas) {
    	this.kodePuskesmas = kodePuskesmas;
    }


    public String getEmailPengguna() {
    	return this.emailPengguna;
    }
    public void setEmailPengguna(String emailPengguna) {
    	this.emailPengguna = emailPengguna;
    }



    public Calendar(){
        super();
    }

    public Calendar(String nomorKalender, String kodePuskesmas, String emailPengguna, List<Gejala> listGejala){
        this.nomorKalender = nomorKalender;
        this.kodePuskesmas = kodePuskesmas;
        this.emailPengguna = emailPengguna;
        this.listGejala = listGejala;
    }


  
}
