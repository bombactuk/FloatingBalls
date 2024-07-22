package shop.paintball.project.entity;

import jakarta.persistence.*;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name= HibernateConstants.CONSTANTS_TABLE_REVIEWS)
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=HibernateConstants.CONSTANTS_COLUMN_ID_REVIEWS)
    private int idReviews;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_CONTENT)
    private String content;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_DATE_POST)
    private LocalDate date_post;

    @OneToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER)
    private User user;

    @OneToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_PRODUCT)
    private Product product;

    public int getIdReviews() {
        return idReviews;
    }

    public void setIdReviews(int idReviews) {
        this.idReviews = idReviews;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviews reviews = (Reviews) o;
        return idReviews == reviews.idReviews && Objects.equals(content, reviews.content) && Objects.equals(date_post, reviews.date_post) && Objects.equals(user, reviews.user) && Objects.equals(product, reviews.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReviews, content, date_post, user, product);
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "idReviews=" + idReviews +
                ", content='" + content + '\'' +
                ", date_post=" + date_post +
                ", user=" + user +
                ", product=" + product +
                '}';
    }

}
