package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int idOrder;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = "name")
    private String name;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = "surname")
    private String surname;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = "telephone")
    private String telephone;

    @Column(name = "status")
    private String status;

    @Column(name = "date_post")
    private LocalDate datePost;

    @Column(name = "tracking_index")
    private String trackingIndex;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderShipping> orderShippings = new HashSet<>();

}
