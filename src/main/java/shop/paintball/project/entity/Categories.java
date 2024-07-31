package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = HibernateConstants.CONSTANTS_TABLE_CATEGORIES)
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_CATEGORIES)
    private int idCategories;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TYPE)
    private String type;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_IMAGE)
    private String image;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Product> products;

    @Transient
    private int productCount;

}

