package com.unir.grupo15.peliculas.model.pojo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PeliculasDto {
    private int id;
    private boolean isOriginal;
    private int videoId;
    private int releaseYear;
    private double requestId;
    private double userRatingRequestId;
    private java.lang.String title;
    private java.lang.String synopsis;
    private java.lang.String numSeasonsLabel;
}
