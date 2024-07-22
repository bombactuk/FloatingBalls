package shop.paintball.project.entity;

import jakarta.persistence.*;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = HibernateConstants.CONSTANTS_TABLE_ROLES)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_ROLE)
    private int idRole;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TITLE)
    private String title;

    @ManyToMany(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_ROLES, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return idRole == role.idRole && Objects.equals(title, role.title) && Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, title, users);
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", title='" + title + '\'' +
                ", users=" + users +
                '}';
    }

}
