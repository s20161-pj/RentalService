package com.rental.service.controller;

import com.rental.service.model.NbpResponse;
import com.rental.service.services.NbpService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nbp")
public class NbpController {
private final NbpService nbpService;
public NbpController(NbpService nbpService){
    this.nbpService=nbpService;
}
@GetMapping(value="/{currency}/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<NbpResponse>calculateRootForCurrency(
                @PathVariable String currency,
                @RequestParam(defaultValue = "1" )int numberOfDays
){

    return ResponseEntity.ok(nbpService.calculateRootForCurrency(currency, numberOfDays));
}
}
