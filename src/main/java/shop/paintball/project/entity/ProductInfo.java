package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = HibernateConstants.CONSTANTS_TABLE_INFO_PRODUCTS)
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_INFO_PRODUCT)
    private int idInfoProduct;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_CONTENT)
    private String content;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_DATE_POST)
    private LocalDate datePost;

    @OneToOne(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_PRODUCT_INFO, cascade = CascadeType.ALL)
    private Product product;

}
