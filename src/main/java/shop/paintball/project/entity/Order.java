package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import shop.paintball.project.entity.constant.HibernateConstants;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"orderShippings"})
@EqualsAndHashCode(exclude = {"orderShippings"})
@Table(name = HibernateConstants.CONSTANTS_TABLE_ORDER)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_ORDER)
    private int idOrder;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_NAME)
    private String name;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_SURNAME)
    private String surname;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TELEPHONE)
    private String telephone;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_STATUS)
    private String status;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_DATE_POST)
    private LocalDate datePost;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TRACKING_INDEX)
    private String trackingIndex;

    @ManyToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = HibernateConstants.CONSTANTS_TABLE_ORDER_PRODUCTS,
            joinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ORDER),
            inverseJoinColumns = @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_PRODUCT)
    )
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_ORDER, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderShipping> orderShippings = new HashSet<>();

}
