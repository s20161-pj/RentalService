package com.rental.service.controller;

import com.rental.service.model.MovieModel;
import com.rental.service.services.RentalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Get movie for given id", response = MovieModel.class, notes = "Endpoint to get movie by id")
   @ApiResponses(value={
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=404, message = "Movie not found"),
            @ApiResponse(code=500, message="Unexpected error")
    })

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieModel> getMovie(
            @ApiParam(name ="movieId", type = "Long", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(this.rentalService.getMovie(id));
    }
    @ApiOperation(value = "Change movie availability", response = MovieModel.class, notes = "Endpoint to change movie status")
    @PutMapping("/movie/available/{id}")
    public ResponseEntity<MovieModel> changeAvailable(
            @ApiParam(name ="movieId", type = "Long", required = true)
            @PathVariable Long id){
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
