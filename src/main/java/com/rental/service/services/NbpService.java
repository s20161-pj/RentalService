package com.rental.service.services;

import com.rental.service.model.NbpResponse;
import com.rental.service.model.Rate;
import com.rental.service.model.Root;
import com.rental.service.repository.NbiApiResponseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class NbpService{
    private final RestTemplate restTemplate;
    private final NbiApiResponseRepository nbiApiResponseRepository;

    public NbpService(RestTemplate restTemplate, NbiApiResponseRepository nbiApiResponseRepository){
        this.restTemplate = restTemplate;
        this.nbiApiResponseRepository = nbiApiResponseRepository;
    }
    public NbpResponse calculateRootForCurrency(String currency, int numberOfDays){
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/"+currency + "/last/" + numberOfDays;
        Root root = restTemplate.getForObject(url, Root.class);
        double average = calculate(root.getRates());
        NbpResponse nbpResponse = getNbpResponse(currency, numberOfDays, average);
        return this.nbiApiResponseRepository.save(nbpResponse);
    }
    private NbpResponse getNbpResponse(String currency, int numberOfDays, double calculate){
        NbpResponse nbpResponse = new NbpResponse();
        nbpResponse.setCurrency(currency);
        nbpResponse.setDays(numberOfDays);
        nbpResponse.setAverage(calculate);
        nbpResponse.setCreatedAt(LocalDateTime.now());
        return nbpResponse;

    }
    public double calculate(List<Rate> rateList){
        double sum=0;
        long count=0;
        for(Rate rate : rateList){
            double mid=rate.getMid();
            sum+=mid;
            count++;
        }
        return  count >0 ? sum/ count : 0.0d;
    }
}
