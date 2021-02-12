package corona.survivor.spring.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component()
public class DataPenerimaDonor {

    private String idDataPenerimaDonor;

    private String NIK;
    private String golonganDarah;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalLahir;
    private boolean isHimself;
    private String domisili;
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalSimpan;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalInfeksi;
    private String emailPendaftar;

    public DataPenerimaDonor(){
        super();
    }

    public DataPenerimaDonor(String idDataPenerimaDonor, String NIK, String golonganDarah, Date tanggalLahir, boolean isHimself, String domisili, String email, Date tanggalSimpan, Date tanggalInfeksi, String emailPendaftar) {
        this.idDataPenerimaDonor = idDataPenerimaDonor;
        this.NIK = NIK;
        this.golonganDarah = golonganDarah;
        this.tanggalLahir = tanggalLahir;
        this.isHimself = isHimself;
        this.domisili = domisili;
        this.email = email;
        this.tanggalSimpan = tanggalSimpan;
        this.tanggalInfeksi = tanggalInfeksi;
        this.emailPendaftar = emailPendaftar;
    }

    public String getIdDataPenerimaDonor() {
        return idDataPenerimaDonor;
    }

    public void setIdDataPenerimaDonor(String idDataPenerimaDonor) {
        this.idDataPenerimaDonor = idDataPenerimaDonor;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public boolean isHimself() {
        return isHimself;
    }

    public void setHimself(boolean himself) {
        isHimself = himself;
    }

    public String getDomisili() {
        return domisili;
    }

    public void setDomisili(String domisili) {
        this.domisili = domisili;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTanggalSimpan() {
        return tanggalSimpan;
    }

    public void setTanggalSimpan(Date tanggalSimpan) {
        this.tanggalSimpan = tanggalSimpan;
    }

    public Date getTanggalInfeksi() {
        return tanggalInfeksi;
    }

    public void setTanggalInfeksi(Date tanggalInfeksi) {
        this.tanggalInfeksi = tanggalInfeksi;
    }

    public String getEmailPendaftar() {
        return emailPendaftar;
    }

    public void setEmailPendaftar(String emailPendaftar) {
        this.emailPendaftar = emailPendaftar;
    }
}
