package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shop")
    private int idShop;

    @Column(name = "address")
    private String address;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "operating_mode")
    private String operatingMode;

    @Column(name = "date_post")
    private LocalDate datePost;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private User user;

}
