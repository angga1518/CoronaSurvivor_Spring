package corona.survivor.spring.model;

import java.util.Date;
import java.util.List;

public class CreateGejalaDTO {

    List<String> listNamaGejala;
    String emailPengguna;
    String nomorKalender;
    Date tanggalDibuat;

    public CreateGejalaDTO(List<String> listNamaGejala, String emailPengguna, String nomorKalender, Date tanggalDibuat){
        this.listNamaGejala = listNamaGejala;
        this.emailPengguna = emailPengguna;
        this.nomorKalender = nomorKalender;
        this.tanggalDibuat = tanggalDibuat;
    }

    public List<String> getListNamaGejala() {
    	return this.listNamaGejala;
    }
    public void setListNamaGejala(List<String> listNamaGejala) {
    	this.listNamaGejala = listNamaGejala;
    }


    public String getEmailPengguna() {
    	return this.emailPengguna;
    }
    public void setEmailPengguna(String emailPengguna) {
    	this.emailPengguna = emailPengguna;
    }


    public String getNomorKalender() {
    	return this.nomorKalender;
    }
    public void setNomorKalender(String nomorKalender) {
    	this.nomorKalender = nomorKalender;
    }


    public Date getTanggalDibuat() {
    	return this.tanggalDibuat;
    }
    public void setTanggalDibuat(Date tanggalDibuat) {
    	this.tanggalDibuat = tanggalDibuat;
    }

    
}
