package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "social_media")
public class SocialMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_social_media")
    private int idSocialMedia;

    @Column(name = "image")
    private String image;

    @Column(name = "link")
    private String link;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private User user;

}
