package corona.survivor.spring.model;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component()
public class Komentar {

    private String idKomentar;
    private String namaLengkap;

    private String tanggalPost;

    private String isi;
    private int jumlahLike;

    private List<String> listIdReply;

    public Komentar(){
        super();
    }

    public Komentar(String idKomentar, String namaLengkap, String tanggalPost, String isi, int jumlahLike, List<String> listIdReply) {
        this.idKomentar = idKomentar;
        this.namaLengkap = namaLengkap;
        this.tanggalPost = tanggalPost;
        this.isi = isi;
        this.jumlahLike = jumlahLike;
        this.listIdReply = listIdReply;
    }

    public String getIdKomentar() {
        return idKomentar;
    }

    public void setIdKomentar(String idKomentar) {
        this.idKomentar = idKomentar;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getTanggalPost() {
        return tanggalPost;
    }

    public void setTanggalPost(String tanggalPost) {
        this.tanggalPost = tanggalPost;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public int getJumlahLike() {
        return jumlahLike;
    }

    public void setJumlahLike(int jumlahLike) {
        this.jumlahLike = jumlahLike;
    }

    public List<String> getListIdReply() {
        return listIdReply;
    }

    public void setListIdReply(List<String> listIdReply) {
        this.listIdReply = listIdReply;
    }
}
