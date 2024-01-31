package com.unir.grupo15.peliculas.c_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.grupo15.peliculas.a_model.a1_pojo.Movies;
import com.unir.grupo15.peliculas.b_data.MoviesRepository;
import com.unir.grupo15.peliculas.a_model.a2_request.MoviesRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class MoviesServiceImpl implements MoviesService {
    @Autowired
    private MoviesRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Movies> getMovies(String title, String synopsis, String numSeasonLabel) {

        if (StringUtils.hasLength(title) || StringUtils.hasLength(synopsis) || StringUtils.hasLength(numSeasonLabel)) {
            return repository.search(title, synopsis, numSeasonLabel);
        }

        List<Movies> movies = repository.getMovies();
        return movies.isEmpty() ? null : movies;
    }

    @Override
    public Movies getMovie(String movieId) {
        return repository.getById(Long.valueOf(movieId));
    }

    @Override
    public Boolean removeMovie(String movieId) {

        Movies product = repository.getById(Long.valueOf(movieId));

        if (product != null) {
            repository.delete(product);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Movies createMovie(MoviesRequest request) {

        //Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
        if (request != null && StringUtils.hasLength(request.getTitle().trim())
                && StringUtils.hasLength(request.getSynopsis().trim())
                && StringUtils.hasLength(request.getNumSeasonsLabel().trim())) {

            Movies product = Movies.builder().title(request.getTitle()).synopsis(request.getSynopsis()).build();

            return repository.save(product);
        } else {
            return null;
        }
    }

    @Override
    public Movies updateMovie(String movieId, String synopsis) {
        //PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
        Movies movies = repository.getById(Long.valueOf(movieId));
        if (movies != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(synopsis));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(movies)));
                Movies patched = objectMapper.treeToValue(target, Movies.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating product {}", movieId, e);
                return null;
            }
        } else {
            return null;
        }
    }
}
