package corona.survivor.spring.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusDaerah {

    @JsonProperty("features")
    private String listKecamatan;

}
