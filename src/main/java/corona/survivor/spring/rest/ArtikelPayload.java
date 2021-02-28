package corona.survivor.spring.rest;

import java.util.Date;
import java.util.List;

public class ArtikelPayload {

    private boolean isLiked;
    private String author;
    private String institusi;
    private String deskripsi;
    private String judul;
    private String idArtikel;
    private Date tanggalPost;
    private int jumlahView;
    private int jumlahLike;
    private List<String> listIdComment;
    private String imageUrl;
    private boolean isSaved = false;

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}
