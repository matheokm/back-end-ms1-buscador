package com.unir.grupo15.peliculas.a_model.a2_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoviesRequest {
    private int videoId;
    private int releaseYear;
    private String title;
    private String synopsis;
    private String numSeasonsLabel;
}
