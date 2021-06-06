package com.rental.service.services;

import com.rental.service.model.MovieModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RentalService {
    private final RestTemplate restTemplate;
    private String MovieServiceUrl = "http://localhost:8080/movies/";

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public MovieModel getMovie(Long movieId) {
        // url GET http://localhost:8080/1
        String url = this.MovieServiceUrl + movieId;
        ResponseEntity<MovieModel> exchange = this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                MovieModel.class);

        return exchange.getBody();
    }

    public MovieModel changeAvailable(Long id) {
        // url PUT http://localhost:8080/available/1
        String url = this.MovieServiceUrl+"available/"+id;

        ResponseEntity<MovieModel> exchange = this.restTemplate.exchange(
                url,
                HttpMethod.PUT,
                HttpEntity.EMPTY,
                MovieModel.class);

        return  exchange.getBody();
    }
}
