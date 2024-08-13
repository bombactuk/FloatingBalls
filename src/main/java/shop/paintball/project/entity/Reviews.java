package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reviews")
    private int idReviews;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 1, max = 1000, message = ValidationMessageConstants.CONSTANTS_MESSAGE_111)
    @Column(name = "content")
    private String content;

    @Column(name = "date_post")
    private LocalDate datePost;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

}
