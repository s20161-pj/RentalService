package com.rental.service.controller;

import com.rental.service.model.MovieModel;
import com.rental.service.services.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
