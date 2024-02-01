package com.unir.grupo15.peliculas.a_model.a1_pojo;

import com.unir.grupo15.peliculas.a_model.a1_pojo.dto.MoviesDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "videoId", unique = true)
    private int videoId;
    @Column(name = "releaseYear")
    private int releaseYear;
    @Column(name = "title")
    private String title;
    @Column(name = "synopsis")
    private String synopsis;
    @Column(name = "numSeasonsLabel")
    private String numSeasonsLabel;

    @ManyToOne
    @JoinColumn(name = "id_gender")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "id_image")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "id_maturity")
    private Maturity maturity;

    public void update(MoviesDto moviesDto) {
        this.videoId = moviesDto.getVideoId();
        this.releaseYear = moviesDto.getReleaseYear();
        this.title = moviesDto.getTitle();
        this.synopsis = moviesDto.getSynopsis();
        this.numSeasonsLabel = moviesDto.getNumSeasonsLabel();
    }
}
