package shop.paintball.project.entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import shop.paintball.project.entity.constant.HibernateConstants;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = HibernateConstants.CONSTANTS_TABLE_USERS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER)
    private int idUser;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Email(message = ValidationMessageConstants.CONSTANTS_MESSAGE_102)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_LOGIN)
    private String login;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 8, max = 15, message = ValidationMessageConstants.CONSTANTS_MESSAGE_103)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_PASSWORD)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_INFO_USER)
    @Valid
    private UserInfo infoUser;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Token token;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = HibernateConstants.CONSTANTS_TABLE_ROLES_HAS_USERS,
            joinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER),
            inverseJoinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ROLE)
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private SocialMedia socialMedia;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private AboutUs aboutUs;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Update update;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Product product;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = HibernateConstants.CONSTANTS_TABLE_FEATURED_PRODUCTS,
            joinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER),
            inverseJoinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_PRODUCT)
    )
    private Set<Product> featuredProducts = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Reviews reviews;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(UserInfo infoUser) {
        this.infoUser = infoUser;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public AboutUs getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(AboutUs aboutUs) {
        this.aboutUs = aboutUs;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Product> getFeaturedProducts() {
        return featuredProducts;
    }

    public void setFeaturedProducts(Set<Product> featuredProducts) {
        this.featuredProducts = featuredProducts;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(infoUser, user.infoUser) && Objects.equals(token, user.token) && Objects.equals(roles, user.roles) && Objects.equals(socialMedia, user.socialMedia) && Objects.equals(aboutUs, user.aboutUs) && Objects.equals(update, user.update) && Objects.equals(product, user.product) && Objects.equals(featuredProducts, user.featuredProducts) && Objects.equals(reviews, user.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, login, password, infoUser, token, roles, socialMedia, aboutUs, update, product, featuredProducts, reviews);
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", infoUser=" + infoUser +
                ", token=" + token +
                ", roles=" + roles +
                ", socialMedia=" + socialMedia +
                ", aboutUs=" + aboutUs +
                ", update=" + update +
                ", product=" + product +
                ", featuredProducts=" + featuredProducts +
                ", reviews=" + reviews +
                '}';
    }

}
