package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images_product")
public class ImageProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_images_product")
    private int idInfoProduct;

    @Column(name = "link")
    private String link;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

}
