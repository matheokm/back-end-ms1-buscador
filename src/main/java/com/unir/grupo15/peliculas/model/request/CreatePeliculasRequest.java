package com.unir.grupo15.peliculas.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePeliculasRequest {
    private String name;
    private String country;
    private String description;
    private Boolean visible;
}
