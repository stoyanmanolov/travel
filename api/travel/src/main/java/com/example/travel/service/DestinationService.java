package com.example.travel.service;

import com.example.travel.model.Destination;
import com.example.travel.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> getDestinations() {
        return destinationRepository.findAll();
    }

    public Destination getDestinationByName(String name) {
        return destinationRepository.findByName(name).orElse(null);
    }

    public Destination createDestination(String name, String country) {
        Destination destination = new Destination(name, country);
        destinationRepository.save(destination);
        return destination;
    }
}
