package com.example.travel.controller;

import com.example.travel.dto.DestinationResponseDto;
import com.example.travel.mapper.DestinationMapper;
import com.example.travel.model.Destination;
import com.example.travel.service.DestinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {
    private final DestinationService destinationService;
    private final DestinationMapper destinationMapper;

    public DestinationController(DestinationService destinationService, DestinationMapper destinationMapper) {
        this.destinationService = destinationService;
        this.destinationMapper = destinationMapper;
    }

    @GetMapping
    public ResponseEntity<List<DestinationResponseDto>> getDestinations() {
        List<Destination> destinations = destinationService.getDestinations();

        return ResponseEntity.ok(
                destinations
                        .stream()
                        .map(destinationMapper::toDto)
                        .toList()
        );
    }
}
