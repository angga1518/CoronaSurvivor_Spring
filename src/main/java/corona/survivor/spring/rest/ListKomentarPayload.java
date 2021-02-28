package corona.survivor.spring.rest;

import java.util.List;

public class ListKomentarPayload {
    List<KomentarPayload> listKomentarPayload;

    public List<KomentarPayload> getListKomentarPayload() {
        return listKomentarPayload;
    }

    public void setListKomentarPayload(List<KomentarPayload> listKomentarPayload) {
        this.listKomentarPayload = listKomentarPayload;
    }
}
