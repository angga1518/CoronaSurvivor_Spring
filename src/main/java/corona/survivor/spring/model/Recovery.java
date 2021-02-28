package corona.survivor.spring.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component()
public class Recovery {

    private String nomorRecovery;
    private String catatanTambahan;
    private String feedback;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalKalender;
    private String nomorKalender;
    private int status;
    //0 belum diisi
    //1 tidak terkoneksi ke puskesmas
    //2 terkoneksi, menunggu balasan
    //3 terkoneksi, sudah dibalas
    private double suhuTubuh;
    private int saturasi;
    private String emailPengguna;

    public String convertTanggal(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-YYYY");
        String dateString = sdf.format(date);
        // System.out.println("aaaaaaa");
        // System.out.println(dateString);
        return dateString;
    }


    public double getSuhuTubuh() {
    	return this.suhuTubuh;
    }
    public void setSuhuTubuh(double suhuTubuh) {
    	this.suhuTubuh = suhuTubuh;
    }

    public int getSaturasi() {
    	return this.saturasi;
    }
    public void setSaturasi(int saturasi) {
    	this.saturasi = saturasi;
    }

    public String getEmailPengguna() {
    	return this.emailPengguna;
    }
    public void setEmailPengguna(String emailPengguna) {
    	this.emailPengguna = emailPengguna;
    }

    public int getStatus() {
    	return this.status;
    }
    public void setStatus(int status) {
    	this.status = status;
    }

    public Recovery(){
        super();
    }

    public Recovery(String nomorRecovery, String catatanTambahan, String feedback, Date tanggalKalender, String nomorKalender, double suhuTubuh, int saturasi, String emailPengguna){
        this.nomorRecovery = nomorRecovery;
        this.catatanTambahan = catatanTambahan;
        this.feedback = feedback;
        this.tanggalKalender = tanggalKalender;
        this.nomorKalender = nomorKalender;
        this.suhuTubuh = suhuTubuh;
        this.saturasi = saturasi;
        this.emailPengguna = emailPengguna;
    }

    public String getNomorRecovery() {
    	return this.nomorRecovery;
    }
    public void setNomorRecovery(String nomorRecovery) {
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
