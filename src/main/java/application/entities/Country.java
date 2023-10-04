package application.entities;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PAYS")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "PAYS")
    private String countrie;
    @OneToMany(mappedBy="country")
    private List<User> utilisateurs;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountrie() {
        return countrie;
    }

    public void setCountrie(String countrie) {
        this.countrie = countrie;
    }
}
