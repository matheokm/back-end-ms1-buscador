package com.unir.grupo15.peliculas.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "madurez")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Madurez {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private java.lang.String value;
    private java.lang.String maturityDescription;
    private java.lang.String specificRatingReason;
    private double maturityLevel;
    //private Peliculas peliculas;
}
