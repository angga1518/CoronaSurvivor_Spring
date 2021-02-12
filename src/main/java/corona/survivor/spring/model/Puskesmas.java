package corona.survivor.spring.model;

import org.springframework.stereotype.Component;

@Component()
public class Puskesmas {

    private String kodePuskesmas;
    private String noTelepon;
    private String namaPuskesmas;
    private String alamat;

    public Puskesmas(){
        super();
    }

    public Puskesmas(String kodePuskesmas, String noTelepon, String namaPuskesmas, String alamat) {
        this.kodePuskesmas = kodePuskesmas;
        this.noTelepon = noTelepon;
        this.namaPuskesmas = namaPuskesmas;
        this.alamat = alamat;
    }

    public String getKodePuskesmas() {
        return kodePuskesmas;
    }

    public void setKodePuskesmas(String kodePuskesmas) {
        this.kodePuskesmas = kodePuskesmas;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getNamaPuskesmas() {
        return namaPuskesmas;
    }

    public void setNamaPuskesmas(String namaPuskesmas) {
        this.namaPuskesmas = namaPuskesmas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
