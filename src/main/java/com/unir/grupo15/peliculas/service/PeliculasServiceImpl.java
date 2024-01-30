package com.unir.grupo15.peliculas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.grupo15.peliculas.data.PeliculasRepository;
import com.unir.grupo15.peliculas.model.pojo.Peliculas;
import com.unir.grupo15.peliculas.model.pojo.dto.PeliculasDto;
import com.unir.grupo15.peliculas.model.request.CreatePeliculasRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class PeliculasServiceImpl implements PeliculasService {
    @Autowired
    private PeliculasRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Peliculas> getProducts(String name, String country, String description, Boolean visible) {

        if (StringUtils.hasLength(name) || StringUtils.hasLength(country) || StringUtils.hasLength(description)
                || visible != null) {
            return repository.search(name, country, description, visible);
        }

        List<Peliculas> products = repository.getProducts();
        return products.isEmpty() ? null : products;
    }

    @Override
    public Peliculas getProduct(String productId) {
        return repository.getById(Long.valueOf(productId));
    }

    @Override
    public Boolean removeProduct(String productId) {

        Peliculas product = repository.getById(Long.valueOf(productId));

        if (product != null) {
            repository.delete(product);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Peliculas createProduct(CreatePeliculasRequest request) {

        //Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
        if (request != null && StringUtils.hasLength(request.getName().trim())
                && StringUtils.hasLength(request.getDescription().trim())
                && StringUtils.hasLength(request.getCountry().trim()) && request.getVisible() != null) {

            Peliculas product = Peliculas.builder().title(request.getName()).synopsis(request.getDescription()).build();

            return repository.save(product);
        } else {
            return null;
        }
    }

    @Override
    public Peliculas updateProduct(String productId, String request) {

        //PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
        Peliculas product = repository.getById(Long.valueOf(productId));
        if (product != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(product)));
                Peliculas patched = objectMapper.treeToValue(target, Peliculas.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating product {}", productId, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Peliculas updateProduct(String productId, PeliculasDto updateRequest) {
        Peliculas product = repository.getById(Long.valueOf(productId));
        if (product != null) {
            product.update(updateRequest);
            repository.save(product);
            return product;
        } else {
            return null;
        }
    }
}
