package shop.paintball.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.paintball.project.entity.constant.ValidationMessageConstants;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "info_users")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_info_user")
    private int idInfoUser;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 3, max = 15, message = ValidationMessageConstants.CONSTANTS_MESSAGE_104)
    @Column(name = "name")
    private String name;

    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Column(name = "birthday")
    private LocalDate birthday;


    @NotNull(message = ValidationMessageConstants.CONSTANTS_MESSAGE_101)
    @Size(min = 6, max = 15, message = ValidationMessageConstants.CONSTANTS_MESSAGE_105)
    @Column(name = "telephone")
    private String telephone;

    @OneToOne(mappedBy = "infoUser")
    private User user;

}
