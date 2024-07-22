package shop.paintball.project.entity;

import jakarta.persistence.*;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = HibernateConstants.CONSTANTS_TABLE_UPDATES)
public class Update {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_UPDATES)
    private int idUpdate;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TITLE)
    private String title;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_CONTENT)
    private String content;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_DATE_POST)
    private LocalDate date_post;

    @OneToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ADMIN)
    private User user;

    public int getIdUpdate() {
        return idUpdate;
    }

    public void setIdUpdate(int idUpdate) {
        this.idUpdate = idUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Update update = (Update) o;
        return idUpdate == update.idUpdate && Objects.equals(title, update.title) && Objects.equals(content, update.content) && Objects.equals(date_post, update.date_post) && Objects.equals(user, update.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUpdate, title, content, date_post, user);
    }

    @Override
    public String toString() {
        return "Update{" +
                "idUpdate=" + idUpdate +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date_post=" + date_post +
                ", user=" + user +
                '}';
    }

}
