package shop.paintball.project.entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"featuredProducts", "roles"})
@EqualsAndHashCode(exclude = {"featuredProducts", "roles"})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Email(message = ValidationMessageConstants.CONSTANTS_MESSAGE_102)
    @Column(name = "login")
    private String login;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 8, message = ValidationMessageConstants.CONSTANTS_MESSAGE_103)
    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_info_user")
    @Valid
    private UserInfo infoUser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "roles_has_users",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<SocialMedia> socialMedia;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Shop> shops;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Update> updates;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Banner> banners;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Product> products;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "featured_products",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private Set<Product> featuredProducts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Reviews> reviews;

}
