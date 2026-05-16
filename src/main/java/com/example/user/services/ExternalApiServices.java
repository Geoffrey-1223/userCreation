package com.example.user.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiServices {

    public String getExternalUsers() {

        String url = "https://jsonplaceholder.typicode.com/users";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, String.class);
    }
}