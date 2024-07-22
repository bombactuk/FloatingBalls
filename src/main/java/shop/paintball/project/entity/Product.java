package shop.paintball.project.entity;


import jakarta.persistence.*;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = HibernateConstants.CONSTANTS_TABLE_PRODUCTS)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_PRODUCT)
    private int idProduct;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_IMAGES)
    private String images;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TITLE)
    private String title;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_PRICE)
    private int price;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_STATUS)
    private String status;

    @OneToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ADMIN)
    private User user;

    @OneToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_INFO_PRODUCT)
    private ProductInfo productInfo;

    @OneToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_CATEGORIES)
    private Categories categories;

    @ManyToMany(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_FEATURED_PRODUCTS)
    private Set<User> users = new HashSet<>();

    @OneToOne(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_PRODUCT, cascade = CascadeType.ALL)
    private Reviews reviews;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", images='" + images + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", productInfo=" + productInfo +
                ", categories=" + categories +
                ", users=" + users +
                ", reviews=" + reviews +
                '}';
    }

}
