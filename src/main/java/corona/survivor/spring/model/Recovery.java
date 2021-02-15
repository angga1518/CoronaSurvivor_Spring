package corona.survivor.spring.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component()
public class Recovery {

    private int nomorRecovery;
    private String catatanTambahan;
    private String feedback;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalKalender;

    private String nomorKalender;

    public Recovery(){
        super();
    }

    public Recovery(int nomorRecovery, String catatanTambahan, String feedback){
        this.nomorRecovery = nomorRecovery;
        this.catatanTambahan = catatanTambahan;
        this.feedback = feedback;
    }

    public int getNomorRecovery() {
    	return this.nomorRecovery;
    }
    public void setNomorRecovery(int nomorRecovery) {
    	this.nomorRecovery = nomorRecovery;
    }


    public String getCatatanTambahan() {
    	return this.catatanTambahan;
    }
    public void setCatatanTambahan(String catatanTambahan) {
    	this.catatanTambahan = catatanTambahan;
    }


    public String getFeedback() {
    	return this.feedback;
    }
    public void setFeedback(String feedback) {
    	this.feedback = feedback;
    }


    public Date getTanggalKalender() {
    	return this.tanggalKalender;
    }
    public void setTanggalKalender(Date tanggalKalender) {
    	this.tanggalKalender = tanggalKalender;
    }


    public String getNomorKalender() {
    	return this.nomorKalender;
    }
    public void setNomorKalender(String nomorKalender) {
    	this.nomorKalender = nomorKalender;
    }




    
}
