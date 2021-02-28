package corona.survivor.spring.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Kecamatan {

    @JsonProperty("kecamatan")
    private String namaKecamatan;

    @JsonProperty("kabupaten")
    private String namaKabupaten;

    @JsonProperty("provinsi")
    private String namaProvinsi;

    @JsonProperty("kategori")
    private String level;
    
}
