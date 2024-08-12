package shop.paintball.project.entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import shop.paintball.project.entity.constant.HibernateConstants;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"featuredProducts", "roles"})
@EqualsAndHashCode(exclude = {"featuredProducts", "roles"})
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
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_PASSWORD)
    @Size(min = 8, message = ValidationMessageConstants.CONSTANTS_MESSAGE_103)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_INFO_USER)
    @Valid
    private UserInfo infoUser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = HibernateConstants.CONSTANTS_TABLE_ROLES_HAS_USERS,
            joinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER),
            inverseJoinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ROLE)
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Set<SocialMedia> socialMedia;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Set<Shop> shops;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Set<Update> updates;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Set<Banner> banners;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Set<Product> products;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = HibernateConstants.CONSTANTS_TABLE_FEATURED_PRODUCTS,
            joinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER),
            inverseJoinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_PRODUCT)
    )
    private Set<Product> featuredProducts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_USER, cascade = CascadeType.ALL)
    private Set<Reviews> reviews;

}
