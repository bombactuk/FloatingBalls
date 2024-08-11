package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.HibernateConstants;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

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

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 3, max = 15, message = ValidationMessageConstants.CONSTANTS_MESSAGE_104)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TYPE)
    private String type;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_IMAGE)
    private String image;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_STATUS)
    private String status;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Product> products;

    @Transient
    private int productCount;

}

