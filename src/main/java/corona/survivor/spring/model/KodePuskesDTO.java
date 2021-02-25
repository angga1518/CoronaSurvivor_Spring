package corona.survivor.spring.model;

public class KodePuskesDTO {
    String kodePuskesmas;
    String nomorKalender;

    public String getNomorKalender() {
    	return this.nomorKalender;
    }
    public void setNomorKalender(String nomorKalender) {
    	this.nomorKalender = nomorKalender;
    }


    public String getKodePuskesmas() {
    	return this.kodePuskesmas;
    }
    public void setKodePuskesmas(String kodePuskesmas) {
    	this.kodePuskesmas = kodePuskesmas;
    }

    public KodePuskesDTO(){
        super();
    }

    public KodePuskesDTO(String kodePuskesmas, String nomorKalender){
        this.kodePuskesmas = kodePuskesmas;
        this.nomorKalender = nomorKalender;
    }

}
