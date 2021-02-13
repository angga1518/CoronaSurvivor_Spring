package corona.survivor.spring.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component()
public class DataPemberiDonor {

    private String idDataPemberiDonor;
    private String namaLengkap;
    private String NIK;
    private String jenisKelamin;
    private String golonganDarah;
    private String domisili;
    private String noTelepon;
    private String email;
    private String rhesus;
    private String beratBadan;
    private String catatanTambahan;
    private boolean isMelahirkan;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalLahir;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalSembuh;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalSimpan;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalInfeksi;

    private String emailPendaftar;

    private List<String> gejala;

    private List<String> riwayatPenyakit;

    private boolean isHimself;

    public DataPemberiDonor(){
        super();
    }

    public DataPemberiDonor(String idDataPemberiDonor, String namaLengkap, String NIK, String jenisKelamin, String golonganDarah, String domisili, String noTelepon, String email, String rhesus, String beratBadan, String catatanTambahan, boolean isMelahirkan, Date tanggalLahir, Date tanggalSembuh, Date tanggalSimpan, Date tanggalInfeksi, String emailPendaftar, List<String> gejala, List<String> riwayatPenyakit, boolean isHimself) {
        this.idDataPemberiDonor = idDataPemberiDonor;
        this.namaLengkap = namaLengkap;
        this.NIK = NIK;
        this.jenisKelamin = jenisKelamin;
        this.golonganDarah = golonganDarah;
        this.domisili = domisili;
        this.noTelepon = noTelepon;
        this.email = email;
        this.rhesus = rhesus;
        this.beratBadan = beratBadan;
        this.catatanTambahan = catatanTambahan;
        this.isMelahirkan = isMelahirkan;
        this.tanggalLahir = tanggalLahir;
        this.tanggalSembuh = tanggalSembuh;
        this.tanggalSimpan = tanggalSimpan;
        this.tanggalInfeksi = tanggalInfeksi;
        this.emailPendaftar = emailPendaftar;
        this.gejala = gejala;
        this.riwayatPenyakit = riwayatPenyakit;
        this.isHimself = isHimself;
    }

    public String getIdDataPemberiDonor() {
        return idDataPemberiDonor;
    }

    public void setIdDataPemberiDonor(String idDataPemberiDonor) {
        this.idDataPemberiDonor = idDataPemberiDonor;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
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

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRhesus() {
        return rhesus;
    }

    public void setRhesus(String rhesus) {
        this.rhesus = rhesus;
    }

    public String getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(String beratBadan) {
        this.beratBadan = beratBadan;
    }

    public String getCatatanTambahan() {
        return catatanTambahan;
    }

    public void setCatatanTambahan(String catatanTambahan) {
        this.catatanTambahan = catatanTambahan;
    }

    public boolean isMelahirkan() {
        return isMelahirkan;
    }

    public void setMelahirkan(boolean melahirkan) {
        isMelahirkan = melahirkan;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Date getTanggalSembuh() {
        return tanggalSembuh;
    }

    public void setTanggalSembuh(Date tanggalSembuh) {
        this.tanggalSembuh = tanggalSembuh;
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

    public List<String> getGejala() {
        return gejala;
    }

    public void setGejala(List<String> gejala) {
        this.gejala = gejala;
    }

    public List<String> getRiwayatPenyakit() {
        return riwayatPenyakit;
    }

    public void setRiwayatPenyakit(List<String> riwayatPenyakit) {
        this.riwayatPenyakit = riwayatPenyakit;
    }

    public boolean isHimself() {
        return isHimself;
    }

    public void setHimself(boolean himself) {
        isHimself = himself;
    }
}
