package ath.hlgo.model;

import org.json.JSONObject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull(groups = Existing.class, message = "Id required.")
    private Integer id;
    @NotEmpty(groups = Existing.class, message = "First Name required.")
    private String firstName;
    @NotEmpty(groups = Existing.class, message = "Last Name required.")
    private String lastName;

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();

        json.put("id", this.id);
        json.put("firstName", this.firstName);
        json.put("lastName", this.lastName);

        return json;
    }

    public interface Existing {
    }
}
