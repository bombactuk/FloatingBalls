package shop.paintball.project.entity;

import jakarta.persistence.*;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.time.LocalDate;

@Entity
@Table(name= HibernateConstants.CONSTANTS_TABLE_ABOUT_US)
public class AboutUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=HibernateConstants.CONSTANTS_COLUMN_ID_ABOUT_US)
    private int idAboutUs;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_CONTENT)
    private String content;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_DATE_POST)
    private LocalDate date_post;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_STATUS)
    private String status;

    @OneToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ADMIN)
    private User user;

    public int getIdAboutUs() {
        return idAboutUs;
    }

    public void setIdAboutUs(int idAboutUs) {
        this.idAboutUs = idAboutUs;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate_post() {
        return date_post;
    }

    public void setDate_post(LocalDate date_post) {
        this.date_post = date_post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AboutUs{" +
                "idAboutUs=" + idAboutUs +
                ", content='" + content + '\'' +
                ", date_post=" + date_post +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }

}
