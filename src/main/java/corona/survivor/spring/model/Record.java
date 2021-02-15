package corona.survivor.spring.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Record {
    private int nomorRecord;

    public int getNomorRecord() {
        return this.nomorRecord;
    }
    public void setNomorRecord(int nomorRecord) {
        this.nomorRecord = nomorRecord;
    }


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalSimpan;

    public Date getTanggalSimpan() {
        return this.tanggalSimpan;
    }
    public void setTanggalSimpan(Date tanggalSimpan) {
        this.tanggalSimpan = tanggalSimpan;
    }


    private String updateCondition;
    private int nomorGejala;

    public String getUpdateCondition() {
    	return this.updateCondition;
    }
    public void setUpdateCondition(String updateCondition) {
    	this.updateCondition = updateCondition;
    }


    public int getNomorGejala() {
    	return this.nomorGejala;
    }
    public void setNomorGejala(int nomorGejala) {
    	this.nomorGejala = nomorGejala;
    }

    public Record(){
        super();
    }

    public Record(int nomorRecord, Date tanggalSimpan, String updateCondition, int nomorGejala){
        this.nomorRecord = nomorRecord;
        this.tanggalSimpan = tanggalSimpan;
        this.updateCondition = updateCondition;
        this.nomorGejala = nomorGejala;
    }

}
