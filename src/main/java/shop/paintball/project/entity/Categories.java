package shop.paintball.project.entity;

import jakarta.persistence.*;
import shop.paintball.project.entity.constant.HibernateConstants;

@Entity
@Table(name = HibernateConstants.CONSTANTS_TABLE_CATEGORIES)
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_CATEGORIES)
    private int idCategories;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TYPE)
    private String type;

    @OneToOne(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_CATEGORIES, cascade = CascadeType.ALL)
    private Product product;

    public int getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(int idCategories) {
        this.idCategories = idCategories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "idCategories=" + idCategories +
                ", type='" + type + '\'' +
                ", product=" + product +
                '}';
    }

}
