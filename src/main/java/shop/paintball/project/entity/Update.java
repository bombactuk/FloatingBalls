package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.HibernateConstants;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = HibernateConstants.CONSTANTS_TABLE_UPDATES)
public class Update {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_UPDATES)
    private int idUpdate;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TITLE)
    private String title;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_CONTENT)
    private String content;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_IMAGE)
    private String image;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_DATE_POST)
    private LocalDate datePost;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_STATUS)
    private String status;

    @ManyToOne
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_ADMIN)
    private User user;

}
