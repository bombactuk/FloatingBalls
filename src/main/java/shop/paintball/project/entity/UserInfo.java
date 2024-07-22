package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import shop.paintball.project.entity.constant.HibernateConstants;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = HibernateConstants.CONSTANTS_TABLE_INFO_USERS)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_INFO_USER)
    private int idInfoUser;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 3, max = 15, message = ValidationMessageConstants.CONSTANTS_MESSAGE_104)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_NAME)
    private String name;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_BIRTHDAY)
    private LocalDate birthday;


    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 6, max = 15, message = ValidationMessageConstants.CONSTANTS_MESSAGE_105)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TELEPHONE)
    private String telephone;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_INFO_USER)
    private User user;

    public int getIdInfoUser() {
        return idInfoUser;
    }

    public void setIdInfoUser(int idInfoUser) {
        this.idInfoUser = idInfoUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return idInfoUser == userInfo.idInfoUser && Objects.equals(name, userInfo.name) && Objects.equals(birthday, userInfo.birthday) && Objects.equals(telephone, userInfo.telephone) && Objects.equals(user, userInfo.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInfoUser, name, birthday, telephone, user);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "idInfoUser=" + idInfoUser +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", telephone='" + telephone + '\'' +
                ", user=" + user +
                '}';
    }

}
