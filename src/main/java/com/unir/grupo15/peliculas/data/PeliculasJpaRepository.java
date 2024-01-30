package com.unir.grupo15.peliculas.data;

import com.unir.grupo15.peliculas.model.pojo.Peliculas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PeliculasJpaRepository extends JpaRepository<Peliculas, Long>, JpaSpecificationExecutor<Peliculas> {
    List<Peliculas> findByName(String name);

    List<Peliculas> findByCountry(String country);

    List<Peliculas> findByVisible(Boolean visible);

    List<Peliculas> findByNameAndCountry(String name, String country);

}
