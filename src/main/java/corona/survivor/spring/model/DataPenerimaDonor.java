package corona.survivor.spring.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component()
public class DataPenerimaDonor {

    private String idDataPenerimaDonor;
    private String namaLengkap;
    private String jenisKelamin;
    private String NIK;
    private String golonganDarah;
    private String domisili;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalLahir;

    private String email;
    private String noTelepon;
    private String rhesus;
    private boolean isHimself;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalSimpan;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalPositif;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalGejala;

    private int beratBadan;

    private List<String> riwayatPenyakit;
    private List<String> gejala;

    private String catatanTambahan;

    private String emailPendaftar;

    public DataPenerimaDonor(){
        super();
    }

    public DataPenerimaDonor(String idDataPenerimaDonor, String namaLengkap, String jenisKelamin, String NIK, String golonganDarah, String domisili, Date tanggalLahir, String email, String noTelepon, String rhesus, boolean isHimself, Date tanggalSimpan, Date tanggalPositif, Date tanggalGejala, int beratBadan, List<String> riwayatPenyakit, List<String> gejala, String catatanTambahan, String emailPendaftar) {
        this.idDataPenerimaDonor = idDataPenerimaDonor;
        this.namaLengkap = namaLengkap;
        this.jenisKelamin = jenisKelamin;
        this.NIK = NIK;
        this.golonganDarah = golonganDarah;
        this.domisili = domisili;
        this.tanggalLahir = tanggalLahir;
        this.email = email;
        this.noTelepon = noTelepon;
        this.rhesus = rhesus;
        this.isHimself = isHimself;
        this.tanggalSimpan = tanggalSimpan;
        this.tanggalPositif = tanggalPositif;
        this.tanggalGejala = tanggalGejala;
        this.beratBadan = beratBadan;
        this.riwayatPenyakit = riwayatPenyakit;
        this.gejala = gejala;
        this.catatanTambahan = catatanTambahan;
        this.emailPendaftar = emailPendaftar;
    }

    public String getIdDataPenerimaDonor() {
        return idDataPenerimaDonor;
    }

    public void setIdDataPenerimaDonor(String idDataPenerimaDonor) {
        this.idDataPenerimaDonor = idDataPenerimaDonor;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
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

    public String getDomisili() {
        return domisili;
    }

    public void setDomisili(String domisili) {
        this.domisili = domisili;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getRhesus() {
        return rhesus;
    }

    public void setRhesus(String rhesus) {
        this.rhesus = rhesus;
    }

    public boolean isHimself() {
        return isHimself;
    }

    public void setHimself(boolean himself) {
        isHimself = himself;
    }

    public Date getTanggalSimpan() {
        return tanggalSimpan;
    }

    public void setTanggalSimpan(Date tanggalSimpan) {
        this.tanggalSimpan = tanggalSimpan;
    }

    public Date getTanggalPositif() {
        return tanggalPositif;
    }

    public void setTanggalPositif(Date tanggalPositif) {
        this.tanggalPositif = tanggalPositif;
    }

    public Date getTanggalGejala() {
        return tanggalGejala;
    }

    public void setTanggalGejala(Date tanggalGejala) {
        this.tanggalGejala = tanggalGejala;
    }

    public int getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(int beratBadan) {
        this.beratBadan = beratBadan;
    }

    public List<String> getRiwayatPenyakit() {
        return riwayatPenyakit;
    }

    public void setRiwayatPenyakit(List<String> riwayatPenyakit) {
        this.riwayatPenyakit = riwayatPenyakit;
    }

    public List<String> getGejala() {
        return gejala;
    }

    public void setGejala(List<String> gejala) {
        this.gejala = gejala;
    }

    public String getCatatanTambahan() {
        return catatanTambahan;
    }

    public void setCatatanTambahan(String catatanTambahan) {
        this.catatanTambahan = catatanTambahan;
    }

    public String getEmailPendaftar() {
        return emailPendaftar;
    }

    public void setEmailPendaftar(String emailPendaftar) {
        this.emailPendaftar = emailPendaftar;
    }
}
