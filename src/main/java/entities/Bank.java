package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_id")
    private int id;

    private String name;

    @ManyToOne
    private User admin;

    public Bank(int id, String name, User admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
    }

    public Bank() {
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

    public boolean isBankAdmin(User user) {
        if(this.admin.getId() == user.getId()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Bank [id=" + id + ", name=" + name + "]";
    }

}