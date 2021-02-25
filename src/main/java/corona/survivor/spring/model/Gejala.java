package corona.survivor.spring.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component()
public class Gejala {
    
    private String nomorGejala;
    private String namaGejala;
    private String emailPengguna;
    private String nomorKalender;
    List<String> sequenceDate = new ArrayList<String>();
    List<String> sequenceUpdate = new ArrayList<String>();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalDibuat;

    public List<String> getSequenceDate() {
    	return this.sequenceDate;
    }
    public void setSequenceDate(List<String> sequenceDate) {
    	this.sequenceDate = sequenceDate;
    }

    public List<String> getSequenceUpdate() {
    	return this.sequenceUpdate;
    }
    public void setSequenceUpdate(List<String> sequenceUpdate) {
    	this.sequenceUpdate = sequenceUpdate;
    }

    public Date getTanggalDibuat() {
    	return this.tanggalDibuat;
    }
    public void setTanggalDibuat(Date tanggalDibuat) {
    	this.tanggalDibuat = tanggalDibuat;
    }

    public String getNomorGejala() {
    	return this.nomorGejala;
    }
    public void setNomorGejala(String nomorGejala) {
    	this.nomorGejala = nomorGejala;
    }

    public String getNamaGejala() {
    	return this.namaGejala;
    }
    public void setNamaGejala(String namaGejala) {
    	this.namaGejala = namaGejala;
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

    public Gejala(String nomorGejala, String namaGejala, String emailPengguna, String nomorKalender, Date tanggalDibuat){
        this.nomorGejala = nomorGejala;
        this.namaGejala = namaGejala;
        this.emailPengguna = emailPengguna;
        this.nomorKalender = nomorKalender;
        this.tanggalDibuat = tanggalDibuat;
    }

    public Gejala(){
        super();
    }
}
