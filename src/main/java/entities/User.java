// package entities;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.OneToOne;
// import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonManagedReference;

// @Entity
// @Table(name = "user")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     @Column(name = "user_id")
//     private int id;

//     private String name;
//     private String mobileNumber;

//     @OneToOne(cascade = CascadeType.ALL)   
//     @JsonManagedReference 
//     private String author;

//     public User(String name, String mobileNumber) {
//         this.name = name;
//         this.mobileNumber = mobileNumber;
//     }

//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public String getMobileNumber() {
//         return this.mobileNumber;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     @Override
//     public String toString() {
//         return "Book [id=" + id + ", name=" + name + "]";
//     }

// }