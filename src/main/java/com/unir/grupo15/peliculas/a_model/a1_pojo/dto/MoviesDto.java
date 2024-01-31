package com.unir.grupo15.peliculas.a_model.a1_pojo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MoviesDto {
    private int videoId;
    private int releaseYear;
    private String title;
    private String synopsis;
    private String numSeasonsLabel;
}
