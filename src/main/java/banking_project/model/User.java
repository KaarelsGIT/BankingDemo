package banking_project.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "full_name", columnDefinition = "VARCHAR(250) NOT NULL")
    private String fullName;

    @Column(name = "mail", columnDefinition = "VARCHAR(255) NOT NULL")
    private String mail;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(45) NOT NULL")
    private String phoneNumber;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                ", fullName='" + fullName + '\'' +
                ", email='" + mail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
