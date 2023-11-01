package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    private String username;
    private String password;
    private String userType;

    private String authToken;

    public User(int id, String username, String password, String userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public void logout(){
        this.authToken = null;
    }

    public boolean validatePassword(String password) {
        if (this.password == null && password == null) {
            return true;
        } else if (this.password == null || password == null) {
            return false;
        }
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", userType=" + userType + "]";
    }

}