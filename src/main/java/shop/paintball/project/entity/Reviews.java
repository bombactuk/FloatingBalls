package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = HibernateConstants.CONSTANTS_TABLE_REVIEWS)
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_REVIEWS)
    private int idReviews;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_CONTENT)
    private String content;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_DATE_POST)
    private LocalDate datePost;

    @ManyToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER)
    private User user;

    @ManyToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_PRODUCT)
    private Product product;

}
