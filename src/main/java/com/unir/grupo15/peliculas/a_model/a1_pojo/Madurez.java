package com.unir.grupo15.peliculas.a_model.a1_pojo;

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
    //private double maturityLevel;
}
