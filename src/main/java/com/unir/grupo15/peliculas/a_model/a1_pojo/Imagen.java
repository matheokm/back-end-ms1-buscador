package com.unir.grupo15.peliculas.a_model.a1_pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagen")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /*private double videoId;
    private java.lang.String url;
    private java.lang.String type;
    private double width;
    private double height;
    private java.lang.String extension;
    private java.lang.String size;
    private java.lang.String imageKey;*/
}
