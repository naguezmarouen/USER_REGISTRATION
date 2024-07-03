package application.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "ROLE")
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
