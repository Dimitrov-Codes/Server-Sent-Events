package com.example.springparkinglot.services;

import com.example.springparkinglot.dto.FloorDTO;
import com.example.springparkinglot.models.Floor;
import com.example.springparkinglot.repository.FloorRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class FloorService {
    private final FloorRepository floorRepository;

    public FloorService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public Mono<Floor> saveFloor(FloorDTO floorDTO) {
        Mono<Floor> detachedFloor = floorRepository.findById(floorDTO.getId()).defaultIfEmpty(new Floor());
        return detachedFloor.flatMap(floor -> {
            floor.setFloor(floorDTO.getFloor());
            return floorRepository.save(floor)
                    .onErrorResume(DuplicateKeyException.class, e -> {
                        System.err.println(e.getMessage());
                        return Mono.error(new DuplicateKeyException("Key Already Existsx"));
                    });
        });
    }

    public Mono<Floor> toggleParkingSpot(String floorId, String parkingSpotId) {
        return floorRepository.findById(floorId)
                .flatMap(floor -> {
                    floor.setParkingData(floor.getParkingData().stream().map(parkingSpot -> {
                        if (parkingSpot.getId().equals(parkingSpotId)) parkingSpot.setStatus(!parkingSpot.getStatus());
                        return parkingSpot;
                    }).collect(Collectors.toList()));
                    return floorRepository.save(floor);
                });
    }
}
