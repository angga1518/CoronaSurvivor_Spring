package corona.survivor.spring.service;

import corona.survivor.spring.rest.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

// import javax.transaction.Transactional;

@Service
// @Transactional
public class StatusDaerahService {
    private final WebClient webClient;

    public StatusDaerahService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://services5.arcgis.com/VS6HdKS0VfIhv8Ct/arcgis/rest/services/Kecamatan_Rawan_COVID19/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json").build();
    }

    public String getAllProvinsi() {

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("where", "1%3D1");
        data.add("outFields", "*");
        data.add("outSR", "4236");
        data.add("f", "json");
        return this.webClient.get().uri(uriBuilder -> uriBuilder.queryParams(data).build()).retrieve().bodyToMono(String.class).block();
    }

    
}
