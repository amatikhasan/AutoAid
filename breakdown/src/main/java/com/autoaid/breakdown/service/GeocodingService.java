package com.autoaid.breakdown.service;

import com.autoaid.breakdown.model.GeoLocation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GeocodingService {

    private static final String GEOCODING_API_URL = "https://api.opencagedata.com/geocode/v1/json";

    public String getCoordinates(String address) {
        RestTemplate restTemplate = new RestTemplate();

        String requestUrl = GEOCODING_API_URL
                + "?q=" + URLEncoder.encode(address, StandardCharsets.UTF_8)
                + "&key=" + "f94be836fb364c2bb78a76deffd4ceb5";

        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);

        // Parse the JSON response to get the coordinates
        // You can use a library like Jackson to do this

        return response.getBody();
    }

    public GeoLocation getCoordinatesFromResponse(String response) {
        GeoLocation geoLocation = new GeoLocation();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(response);

            // Get the first result from the results array
            JsonNode resultsNode = rootNode.get("results").get(0);

            // Get the geometry object from the result
            JsonNode geometryNode = resultsNode.get("geometry");

            // Extract latitude and longitude
            double lat = geometryNode.get("lat").asDouble();
            double lng = geometryNode.get("lng").asDouble();

            System.out.println("Latitude: " + lat);
            System.out.println("Longitude: " + lng);

            geoLocation.setLatitude(lat);
            geoLocation.setLongitude(lng);
            return geoLocation;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
