package shop.paintball.project.entity;

import jakarta.persistence.*;
import shop.paintball.project.entity.constant.HibernateConstants;

@Entity
@Table(name= HibernateConstants.CONSTANTS_TABLE_TOKENS)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=HibernateConstants.CONSTANTS_COLUMN_ID_TOKENS)
    private int idToken;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_NUMBER)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER)
    private User user;

    public int getIdToken() {
        return idToken;
    }

    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Token{" +
                "idToken=" + idToken +
                ", number='" + number + '\'' +
                ", user=" + user +
                '}';
    }

}
