package com.innova.loan.utils;

import com.innova.loan.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created for Micro Services
 * Getting informations from Customer Service
 * customers monthly income and phone number are used in this project
 *
 */
@Component
public class RestService {

    @Autowired
    private Environment environment;


    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     *
     * @param customerId to comminicate with Customer Project
     * @return informations as JSON format
     */
    public CustomerDto getPostsPlainJSON(Integer customerId) {
        String url = environment.getProperty("customer.url").concat("/{id}");
        ResponseEntity<CustomerDto> response = this.restTemplate.getForEntity(url, CustomerDto.class, customerId);
        return response.getBody();

    }
}