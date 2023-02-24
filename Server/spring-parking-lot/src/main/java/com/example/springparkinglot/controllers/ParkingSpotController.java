package com.example.springparkinglot.controllers;

import com.example.springparkinglot.dto.ParkingSpotDTO;
import com.example.springparkinglot.models.Floor;
import com.example.springparkinglot.models.ParkingSpot;
import com.example.springparkinglot.services.FloorService;
import com.example.springparkinglot.services.ParkingSpotService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/parkingSpot")
public class ParkingSpotController {
    private final FloorService floorService;
    private final ParkingSpotService parkingSpotService;

    public ParkingSpotController(FloorService floorService, ParkingSpotService parkingSpotService) {
        this.floorService = floorService;
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping(value = "/addParkingSpot", consumes = "application/json")
    public Mono<Floor> addParkingSpot(@RequestBody ParkingSpotDTO parkingSpotDTO){
        return parkingSpotService.save(parkingSpotDTO);
    }
    @DeleteMapping("/deleteParkingSpot")
    public Mono<Floor> deleteParkingSpot(@RequestBody ParkingSpotDTO parkingSpotDTO){
        return parkingSpotService.deleteParkingSpot(parkingSpotDTO);
    }


}
