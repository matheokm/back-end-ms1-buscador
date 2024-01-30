package com.unir.grupo15.peliculas.service;

import com.unir.grupo15.peliculas.model.pojo.Peliculas;
import com.unir.grupo15.peliculas.model.pojo.dto.PeliculasDto;
import com.unir.grupo15.peliculas.model.request.CreatePeliculasRequest;

import java.util.List;

public interface PeliculasService {
    List<Peliculas> getProducts(String name, String country, String description, Boolean visible);

    Peliculas getProduct(String productId);

    Boolean removeProduct(String productId);

    Peliculas createProduct(CreatePeliculasRequest request);

    Peliculas updateProduct(String productId, String updateRequest);

    Peliculas updateProduct(String productId, PeliculasDto updateRequest);
}
