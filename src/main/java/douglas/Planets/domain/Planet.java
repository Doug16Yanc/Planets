package douglas.Planets.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "planets")
public class Planet {
   @Id
    private int id;
    private String name;
    private String climate;
    private String ground;

    public Planet(){
    }

    public Planet(int id, String name, String climate, String ground) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.ground = ground;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }
}
