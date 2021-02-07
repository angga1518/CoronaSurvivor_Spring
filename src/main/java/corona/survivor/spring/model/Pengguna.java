package corona.survivor.spring.model;

import org.springframework.stereotype.Component;

@Component()
public class Pengguna {

    private String id;
    private String email;
    private String name;

    public Pengguna() {
        super();
    }

    public Pengguna(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
