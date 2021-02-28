package corona.survivor.spring.model;

// import org.springframework.format.annotation.StringTimeFormat;
import org.springframework.stereotype.Component;

// import java.util.String;
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
    private String melahirkan;

    // @StringTimeFormat(iso = StringTimeFormat.ISO.String)
    private String tanggalLahir;

    // @StringTimeFormat(iso = StringTimeFormat.ISO.String)
    private String tanggalSembuh;

    // @StringTimeFormat(iso = StringTimeFormat.ISO.String)
    private String tanggalSimpan;

    // @StringTimeFormat(iso = StringTimeFormat.ISO.String)
    private String tanggalInfeksi;

    private String emailPendaftar;

    private List<String> gejala;

    private List<String> riwayatPenyakit;

    private boolean isHimself;

    public DataPemberiDonor(){
        super();
    }

    public DataPemberiDonor(String idDataPemberiDonor, String namaLengkap, String NIK, String jenisKelamin, String golonganDarah, String domisili, String noTelepon, String email, String rhesus, String beratBadan, String catatanTambahan, String melahirkan, String tanggalLahir, String tanggalSembuh, String tanggalSimpan, String tanggalInfeksi, String emailPendaftar, List<String> gejala, List<String> riwayatPenyakit, boolean isHimself) {
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
        this.melahirkan = melahirkan;
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

    public String getMelahirkan() {
        return melahirkan;
    }

    public void setMelahirkan(String melahirkan) {
        this.melahirkan = melahirkan;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTanggalSembuh() {
        return tanggalSembuh;
    }

    public void setTanggalSembuh(String tanggalSembuh) {
        this.tanggalSembuh = tanggalSembuh;
    }

    public String getTanggalSimpan() {
        return tanggalSimpan;
    }

    public void setTanggalSimpan(String tanggalSimpan) {
        this.tanggalSimpan = tanggalSimpan;
    }

    public String getTanggalInfeksi() {
        return tanggalInfeksi;
    }

    public void setTanggalInfeksi(String tanggalInfeksi) {
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
