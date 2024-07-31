package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.HibernateConstants;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = HibernateConstants.CONSTANTS_TABLE_INFO_USERS)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_ID_INFO_USER)
    private int idInfoUser;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 3, max = 15, message = ValidationMessageConstants.CONSTANTS_MESSAGE_104)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_NAME)
    private String name;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_BIRTHDAY)
    private LocalDate birthday;


    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 6, max = 15, message = ValidationMessageConstants.CONSTANTS_MESSAGE_105)
    @Column(name = HibernateConstants.CONSTANTS_COLUMN_TELEPHONE)
    private String telephone;

    @OneToOne(mappedBy = HibernateConstants.CONSTANTS_MAPPED_BY_INFO_USER)
    private User user;

}
