package com.rental.service.controller;

import com.rental.service.model.MovieModel;
import com.rental.service.services.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieModel> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(this.rentalService.getMovie(id));
    }

    @PutMapping("/movie/available/{id}")
    public ResponseEntity<MovieModel> changeAvailable(@PathVariable Long id){
        MovieModel movieUpdated = this.rentalService.changeAvailable(id);

        if(movieUpdated == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(movieUpdated);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleClientErrorException(
            HttpClientErrorException exception
    ) {
        int codeStatus = exception.getRawStatusCode();
        if(codeStatus == HttpStatus.NOT_FOUND.value()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(codeStatus == HttpStatus.BAD_REQUEST.value()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleHttpServerException(
            HttpServerErrorException exception
    ) {
        int codeStatus = exception.getRawStatusCode();

        if(codeStatus == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceAccessException(
            ResourceAccessException exception
    ) {
         return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).build();
    }
}
