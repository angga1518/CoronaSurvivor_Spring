package corona.survivor.spring.rest;

import corona.survivor.spring.model.Komentar;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public class KomentarPayload {

    private String idKomentar;
    private String namaLengkap;

    private String tanggalPost;

    private String isi;
    private int jumlahLike;

    private List<Komentar> replies;
    private boolean isLiked;

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

    public List<Komentar> getReplies() {
        return replies;
    }

    public void setReplies(List<Komentar> replies) {
        this.replies = replies;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
