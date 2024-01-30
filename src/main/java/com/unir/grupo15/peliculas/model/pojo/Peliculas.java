package com.unir.grupo15.peliculas.model.pojo;

import com.unir.grupo15.peliculas.model.pojo.dto.PeliculasDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pelicula")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Peliculas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isOriginal;
    private int videoId;
    private int releaseYear;
    private double requestId;
    private double userRatingRequestId;
    private java.lang.String title;
    private java.lang.String synopsis;
    private java.lang.String numSeasonsLabel;
    @OneToMany(mappedBy = "pelicula", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Genero> generos;
    @OneToOne(mappedBy = "pelicula", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    private Imagen imagenFondo;
    @OneToOne(mappedBy = "pelicula", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    private Imagen imagenLogo;
    @OneToMany(mappedBy = "pelicula", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Madurez> madurez;

    public void update(PeliculasDto peliculasDto) {
        this.title = peliculasDto.getTitle();
        this.synopsis = peliculasDto.getSynopsis();
    }
}
