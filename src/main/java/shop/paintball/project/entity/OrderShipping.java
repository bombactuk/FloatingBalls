package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import shop.paintball.project.entity.constant.HibernateConstants;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

@Entity
@Data
@ToString(exclude = {"order"})
@EqualsAndHashCode(exclude = {"order"})
@NoArgsConstructor
@Table(name = HibernateConstants.CONSTANTS_TABLE_ORDER_SHIPPING)
public class OrderShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_ORDER_SHIPPING)
    private int idOrderShipping;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_COUNTRY)
    private String country;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ADDRESS)
    private String address;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_POSTAL_CODE)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ORDER)
    private Order order;

}
