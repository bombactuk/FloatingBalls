package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "banners")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_banner")
    private int idBanner;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "date_post")
    private LocalDate datePost;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private User user;

}

