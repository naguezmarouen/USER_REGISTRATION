package application.entities;

import application.enums.Gender;
import com.sun.istack.NotNull;
import jdk.jfr.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "UTILISATEUR")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(name = "NOM", nullable = false)
    @NotBlank(message = "Nom obligatoire")
    private String userName;
    @Column(name = "DATE_NAISSANCE", nullable = false)
    @Timestamp
    private LocalDate birthDateUser;
    @Column(name = "TELEPHONE")
    private String phone;
    @Column(name = "SEXE")
    private Gender gender;

    @ManyToOne(optional = false,cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Country country;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getBirthDateUser() {
        return birthDateUser;
    }

    public void setBirthDateUser(LocalDate birthDateUser) {
        this.birthDateUser = birthDateUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
