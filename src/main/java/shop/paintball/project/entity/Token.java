package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.HibernateConstants;

@Entity
@Data
@NoArgsConstructor
@Table(name = HibernateConstants.CONSTANTS_TABLE_TOKENS)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_TOKENS)
    private int idToken;

    @Column(name = HibernateConstants.CONSTANTS_COLUMN_NUMBER)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = HibernateConstants.CONSTANTS_COLUMN_ID_USER)
    private User user;

}
