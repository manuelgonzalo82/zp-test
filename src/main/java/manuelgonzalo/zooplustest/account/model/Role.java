package manuelgonzalo.zooplustest.account.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by manuel on 24/6/17.
 */
@Entity
@Table(name = "role")
public class Role {
    private Long id;
    private String role;
    private Set<User> users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
