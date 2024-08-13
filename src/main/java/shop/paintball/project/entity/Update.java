package shop.paintball.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "updates")
public class Update {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_updates")
    private int idUpdate;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

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
