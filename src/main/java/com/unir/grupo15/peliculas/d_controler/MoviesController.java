package com.unir.grupo15.peliculas.d_controler;

import com.unir.grupo15.peliculas.a_model.a1_pojo.Movies;
import com.unir.grupo15.peliculas.a_model.a1_pojo.dto.MoviesDto;
import com.unir.grupo15.peliculas.a_model.a2_request.MoviesRequest;
import com.unir.grupo15.peliculas.c_service.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Movies Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre peliculas alojados en una base de datos en memoria.")

public class MoviesController {
    private final MoviesService service;
    @GetMapping("/movies")
    @Operation(
            operationId = "Obtener peliculas",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos las películas almacenadas en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movies.class)))
    public ResponseEntity<List<Movies>> getProducts(
            @RequestHeader Map<String, String> headers,

            @Parameter(name = "title", description = "Título de la película", required = false)
            @RequestParam(required = false) String title,

            @Parameter(name = "synopsis", description = "Sinopsis de la película.",  required = false)
            @RequestParam(required = false) String synopsis,

            @Parameter(name = "temporadas", description = "Numero de temporadas", required = false)
            @RequestParam(required = false) String numSeasonLabel) {

        log.info("headers: {}", headers);
        List<Movies> movies = service.getMovies(title, synopsis, numSeasonLabel);

        if (movies != null) {
            return ResponseEntity.ok(movies);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/movies/{movieId}")
    @Operation(
            operationId = "Obtener información de una película",
            description = "Operacion de lectura",
            summary = "Devuelve información de una película a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movies.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la película con el identificador indicado.")
    public ResponseEntity<Movies> getMovie(@PathVariable String movieId) {

        log.info("Request received for product {}", movieId);
        Movies product = service.getMovie(movieId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/movies/{movieId}")
    @Operation(
            operationId = "Eliminar una película",
            description = "Operacion de escritura",
            summary = "Se elimina una pelicula a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la película con el identificador indicado.")
    public ResponseEntity<Void> deleteMovie(@PathVariable String movieId) {

        Boolean removed = service.removeMovie(movieId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
