package shop.paintball.project.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "productInfo", "users", "reviews"})
@EqualsAndHashCode(exclude = {"user", "productInfo", "users", "reviews"})
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_info_product")
    private ProductInfo productInfo;

    @ManyToOne
    @JoinColumn(name = "id_categories")
    private Categories categories;

    @ManyToMany(mappedBy = "featuredProducts", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Reviews> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ImageProduct> images = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

}
