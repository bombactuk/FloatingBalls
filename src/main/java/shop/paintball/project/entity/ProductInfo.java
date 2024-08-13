package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "info_product")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_info_product")
    private int idInfoProduct;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = "content")
    private String content;

    @Column(name = "date_post")
    private LocalDate datePost;

    @OneToOne(mappedBy = "productInfo", cascade = CascadeType.ALL)
    private Product product;

}
