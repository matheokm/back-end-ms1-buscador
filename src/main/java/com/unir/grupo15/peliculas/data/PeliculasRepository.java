package com.unir.grupo15.peliculas.data;

import com.unir.grupo15.peliculas.data.utils.SearchCriteria;
import com.unir.grupo15.peliculas.data.utils.SearchOperation;
import com.unir.grupo15.peliculas.data.utils.SearchStatement;
import com.unir.grupo15.peliculas.model.pojo.Peliculas;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class PeliculasRepository {

    private final PeliculasJpaRepository repository;

    public List<Peliculas> getProducts() {
        return repository.findAll();
    }

    public Peliculas getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Peliculas save(Peliculas product) {
        return repository.save(product);
    }

    public void delete(Peliculas product) {
        repository.delete(product);
    }

    public List<Peliculas> search(String name, String country, String description, Boolean visible) {
        SearchCriteria<Peliculas> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(name)) {
            spec.add(new SearchStatement("name", name, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(country)) {
            spec.add(new SearchStatement("country", country, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(description)) {
            spec.add(new SearchStatement("description", description, SearchOperation.MATCH));
        }

        if (visible != null) {
            spec.add(new SearchStatement("visible", visible, SearchOperation.EQUAL));
        }
        return repository.findAll(spec);
    }
}
