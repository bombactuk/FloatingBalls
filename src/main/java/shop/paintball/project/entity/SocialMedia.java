package shop.paintball.project.entity;

import jakarta.persistence.*;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.util.Objects;

@Entity
@Table(name= HibernateConstants.CONSTANTS_TABLE_SOCIAL_MEDIA)
public class SocialMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=HibernateConstants.CONSTANTS_COLUMN_ID_SOCIAL_MEDIA)
    private int idSocialMedia;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_IMAGE)
    private String image;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_LINK)
    private String link;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_STATUS)
    private String status;

    @OneToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ADMIN)
    private User user;

    public int getIdSocialMedia() {
        return idSocialMedia;
    }

    public void setIdSocialMedia(int idSocialMedia) {
        this.idSocialMedia = idSocialMedia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialMedia that = (SocialMedia) o;
        return idSocialMedia == that.idSocialMedia && Objects.equals(image, that.image) && Objects.equals(link, that.link) && Objects.equals(status, that.status) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSocialMedia, image, link, status, user);
    }

    @Override
    public String toString() {
        return "SocialMedia{" +
                "idSocialMedia=" + idSocialMedia +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }

}
