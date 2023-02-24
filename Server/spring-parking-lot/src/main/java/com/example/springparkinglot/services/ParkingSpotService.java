package com.example.springparkinglot.services;

import com.example.springparkinglot.dto.ParkingSpotDTO;
import com.example.springparkinglot.models.Floor;
import com.example.springparkinglot.models.ParkingSpot;
import com.example.springparkinglot.repository.FloorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class ParkingSpotService {
    private final FloorRepository floorRepository;

    public ParkingSpotService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public Mono<Floor> save(ParkingSpotDTO parkingSpotDTO) {
        ParkingSpot parkingSpot = convertToParkingSpot(parkingSpotDTO);
        return floorRepository.findById(parkingSpotDTO.getFloorId())
                .flatMap(floor -> {
                    floor.getParkingData().add(parkingSpot);
                    return floorRepository.save(floor);
                });

    }

    private ParkingSpot convertToParkingSpot(ParkingSpotDTO parkingSpotDTO) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setId(parkingSpotDTO.getParkingSpotId());
        parkingSpot.setStatus(parkingSpotDTO.getStatus());
        return parkingSpot;
    }

    public Mono<Floor> deleteParkingSpot(ParkingSpotDTO parkingSpotDTO) {
        return floorRepository.findById(parkingSpotDTO.getFloorId())
                .flatMap(floor -> {
                    floor.setParkingData(floor.getParkingData()
                            .stream()
                            .filter(parkingSpot -> !parkingSpot.getId().equals(parkingSpotDTO.getParkingSpotId()))
                            .collect(Collectors.toList()));
                    return floorRepository.save(floor);
                });
    }
}
