package corona.survivor.spring.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component()
public class Calendar {

    private String nomorKalender;
    private String kodePuskesmas;
    private String emailPengguna;
    private List<String> listGejala = new ArrayList<String>();
    private String nik;
    private int beratBadan;
    private int red;
    private int yellow;
    private boolean isDelayed;
    private boolean allGejalaAwalAdded;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalDibuat;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startRed;
    private int status;
    //1 = OTG
    //2 = Bergejala
    //3 = Parah
    private List<String> listRecovery = new ArrayList<String>();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalPositif;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalMunculGejala;

    private Date lastDate;

    public Date getLastDate() {
    	return this.lastDate;
    }
    public void setLastDate(Date lastDate) {
    	this.lastDate = lastDate;
    }


    public List<String> getListRecovery() {
    	return this.listRecovery;
    }
    public void setListRecovery(List<String> listRecovery) {
    	this.listRecovery = listRecovery;
    }

    public boolean getAllGejalaAwalAdded() {
    	return this.allGejalaAwalAdded;
    }
    public void setAllGejalaAwalAdded(boolean allGejalaAwalAdded) {
    	this.allGejalaAwalAdded = allGejalaAwalAdded;
    }

    public Date getTanggalDibuat() {
    	return this.tanggalDibuat;
    }
    public void setTanggalDibuat(Date tanggalDibuat) {
    	this.tanggalDibuat = tanggalDibuat;
    }

    public boolean getIsDelayed() {
    	return this.isDelayed;
    }
    public void setIsDelayed(boolean isDelayed) {
    	this.isDelayed = isDelayed;
    }

    public Date getStartRed() {
    	return this.startRed;
    }
    public void setStartRed(Date startRed) {
    	this.startRed = startRed;
    }

    public int getRed() {
    	return this.red;
    }
    public void setRed(int red) {
    	this.red = red;
    }

    public int getYellow() {
    	return this.yellow;
    }
    public void setYellow(int yellow) {
    	this.yellow = yellow;
    }

    public int getStatus() {
    	return this.status;
    }
    public void setStatus(int status) {
    	this.status = status;
    }


    public String getNik() {
    	return this.nik;
    }
    public void setNik(String nik) {
    	this.nik = nik;
    }


    public int getBeratBadan() {
    	return this.beratBadan;
    }
    public void setBeratBadan(int beratBadan) {
    	this.beratBadan = beratBadan;
    }

    public Date getTanggalPositif() {
    	return this.tanggalPositif;
    }
    public void setTanggalPositif(Date tanggalPositif) {
    	this.tanggalPositif = tanggalPositif;
    }

    public Date getTanggalMunculGejala() {
    	return this.tanggalMunculGejala;
    }
    public void setTanggalMunculGejala(Date tanggalMunculGejala) {
    	this.tanggalMunculGejala = tanggalMunculGejala;
    }

    public List<String> getListGejala() {
        return this.listGejala;
    }
    public void setListGejala(List<String> listGejala) {
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

    public Calendar(String nomorKalender, String kodePuskesmas, String emailPengguna,  String nik,
    int beratBadan, Date tanggalDibuat, Date tanggalPositif, Date tanggalMunculGejala){
        this.nomorKalender = nomorKalender;
        this.kodePuskesmas = kodePuskesmas;
        this.emailPengguna = emailPengguna;
        this.nik = nik;
        this.beratBadan = beratBadan;
        this.isDelayed = false;
        this.allGejalaAwalAdded = false;
        this.tanggalDibuat = tanggalDibuat;
        this.tanggalPositif = tanggalPositif;
        this.tanggalMunculGejala = tanggalMunculGejala;
    }
       
}
