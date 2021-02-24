package corona.survivor.spring.model;

public class GejalaDTO {
    String uuid;
    String update;
    String date;

    public String getDate() {
    	return this.date;
    }
    public void setDate(String date) {
    	this.date = date;
    }


    public String getUuid() {
    	return this.uuid;
    }
    public void setUuid(String uuid) {
    	this.uuid = uuid;
    }


    public String getUpdate() {
    	return this.update;
    }
    public void setUpdate(String update) {
    	this.update = update;
    }

    public GejalaDTO(){
        super();
    }

    public GejalaDTO(String uuid, String update){
        this.uuid = uuid;
        this.update = update;
    }
}
