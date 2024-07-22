package shop.paintball.project.entity;

import jakarta.persistence.*;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name= HibernateConstants.CONSTANTS_TABLE_INFO_PRODUCTS)
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=HibernateConstants.CONSTANTS_COLUMN_ID_INFO_PRODUCT)
    private int idInfoProduct;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_CONTENT)
    private String content;

    @Column(name=HibernateConstants.CONSTANTS_COLUMN_DATE_POST)
    private LocalDate post_date;

    @OneToOne(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_PRODUCT_INFO, cascade = CascadeType.ALL)
    private Product product;

    public int getIdInfoProduct() {
        return idInfoProduct;
    }

    public void setIdInfoProduct(int idInfoProduct) {
        this.idInfoProduct = idInfoProduct;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDate post_date) {
        this.post_date = post_date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfo that = (ProductInfo) o;
        return idInfoProduct == that.idInfoProduct && Objects.equals(content, that.content) && Objects.equals(post_date, that.post_date) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInfoProduct, content, post_date, product);
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "idInfoProduct=" + idInfoProduct +
                ", content='" + content + '\'' +
                ", post_date=" + post_date +
                ", product=" + product +
                '}';
    }

}
