package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.HibernateConstants;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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

}
