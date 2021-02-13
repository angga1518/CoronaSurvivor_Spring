package corona.survivor.spring.model;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component()
public class Artikel {

    private String author;
    private String institusi;
    private String deskripsi;
    private String judul;
    private String idArtikel;
    private Date tanggalPost;
    private int jumlahView;
    private int jumlahLike;
    private List<String> listIdComment;

    public Artikel(){
        super();
    }

    public Artikel(String author, String institusi, String deskripsi, String judul, String idArtikel, Date tanggalPost, int jumlahView, int jumlahLike, List<String> listIdComment) {
        this.author = author;
        this.institusi = institusi;
        this.deskripsi = deskripsi;
        this.judul = judul;
        this.idArtikel = idArtikel;
        this.tanggalPost = tanggalPost;
        this.jumlahView = jumlahView;
        this.jumlahLike = jumlahLike;
        this.listIdComment = listIdComment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIdArtikel() {
        return idArtikel;
    }

    public void setIdArtikel(String idArtikel) {
        this.idArtikel = idArtikel;
    }

    public Date getTanggalPost() {
        return tanggalPost;
    }

    public void setTanggalPost(Date tanggalPost) {
        this.tanggalPost = tanggalPost;
    }

    public int getJumlahView() {
        return jumlahView;
    }

    public void setJumlahView(int jumlahView) {
        this.jumlahView = jumlahView;
    }

    public int getJumlahLike() {
        return jumlahLike;
    }

    public void setJumlahLike(int jumlahLike) {
        this.jumlahLike = jumlahLike;
    }

    public List<String> getListIdComment() {
        return listIdComment;
    }

    public void setListIdComment(List<String> listIdComment) {
        this.listIdComment = listIdComment;
    }
}
