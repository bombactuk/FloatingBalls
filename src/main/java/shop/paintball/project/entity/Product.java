package shop.paintball.project.entity;


import jakarta.persistence.*;
import lombok.*;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "productInfo", "users", "reviews"})
@EqualsAndHashCode(exclude = {"user", "productInfo", "users", "reviews"})
@Table(name = HibernateConstants.CONSTANTS_TABLE_PRODUCTS)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_PRODUCT)
    private int idProduct;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TITLE)
    private String title;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_PRICE)
    private int price;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_STATUS)
    private String status;

    @ManyToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ADMIN)
    private User user;

    @OneToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_INFO_PRODUCT)
    private ProductInfo productInfo;

    @ManyToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_CATEGORIES)
    private Categories categories;

    @ManyToMany(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_FEATURED_PRODUCTS, fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_PRODUCT, cascade = CascadeType.ALL)
    private Set<Reviews> reviews;

    @OneToMany(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_PRODUCT, cascade = CascadeType.ALL)
    private List<ImageProduct> images = new ArrayList<>();

    @ManyToMany(mappedBy = HibernateConstants.CONSTANTS_TABLE_PRODUCTS)
    private List<Order> orders = new ArrayList<>();

}
