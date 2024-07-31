package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.HibernateConstants;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = HibernateConstants.CONSTANTS_TABLE_IMAGES_PRODUCT)
public class ImageProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_IMAGES_PRODUCT)
    private int idInfoProduct;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_LINK)
    private String link;

    @ManyToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_PRODUCT)
    private Product product;

}
