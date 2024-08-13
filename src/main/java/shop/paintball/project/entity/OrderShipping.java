package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

@Entity
@Data
@ToString(exclude = {"order"})
@EqualsAndHashCode(exclude = {"order"})
@NoArgsConstructor
@Table(name = "orders-shipping")
public class OrderShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_shipping")
    private int idOrderShipping;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = "country")
    private String country;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = "address")
    private String address;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = "postal_code")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

}
