package corona.survivor.spring.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component()
public class Pengguna {

    private String email;
    private String namaLengkap;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalLahir;

    private String jenisKelamin;
    private String domisili;
    private String noTelepon;
    private List<String> listIdArtikelDisimpan;

    public Pengguna() {
        super();
    }

    public Pengguna(String email, String namaLengkap, Date tanggalLahir, String jenisKelamin, String domisili, String noTelepon, List<String> listIdArtikelDisimpan) {
        this.email = email;
        this.namaLengkap = namaLengkap;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.domisili = domisili;
        this.noTelepon = noTelepon;
        this.listIdArtikelDisimpan = listIdArtikelDisimpan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getDomisili() {
        return domisili;
    }

    public void setDomisili(String domisili) {
        this.domisili = domisili;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public List<String> getListIdArtikelDisimpan() {
        return listIdArtikelDisimpan;
    }

    public void setListIdArtikelDisimpan(List<String> listIdArtikelDisimpan) {
        this.listIdArtikelDisimpan = listIdArtikelDisimpan;
    }
}
