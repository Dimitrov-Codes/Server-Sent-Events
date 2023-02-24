package com.example.springparkinglot.controllers;

import com.example.springparkinglot.dto.FloorDTO;
import com.example.springparkinglot.models.Floor;
import com.example.springparkinglot.repository.FloorRepository;
import com.example.springparkinglot.services.FloorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/floor")
@Slf4j
public class FloorController {
    private final FloorRepository floorRepository;
    private final FloorService floorService;
    private final Sinks.Many<Floor> sink;

    public FloorController(FloorRepository floorRepository, FloorService floorService) {
        this.floorRepository = floorRepository;
        this.floorService = floorService;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }


    @GetMapping(value = "/get", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Floor> getParkingData() {

        return sink.asFlux().mergeWith(floorRepository.findAll()).doOnNext(sink::tryEmitNext);

    }

    @PatchMapping("/toggleParkingSpot/{floorId}&&{parkingSpotId}")
    public Mono<Floor> toggleParkingSpot(@PathVariable String floorId, @PathVariable String parkingSpotId) {
        return floorService.toggleParkingSpot(floorId, parkingSpotId).doOnNext(sink::tryEmitNext);
    }

    @PostMapping(value = "/createFloor", consumes = "application/json")
    public Mono<ResponseEntity<Floor>> createFloor(@RequestBody FloorDTO floorDTO) {
        return floorService.saveFloor(floorDTO).doOnNext(sink::tryEmitNext).map(ResponseEntity::ok).onErrorResume(DuplicateKeyException.class, e -> {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        });
    }

    @PatchMapping("/updateFloor")
    public Mono<Floor> updateFloor(@ModelAttribute FloorDTO floorDTO) {
        return floorService.saveFloor(floorDTO).doOnNext(sink::tryEmitNext);

    }

    @DeleteMapping("/deleteFloor/{id}")
    public Mono<Void> deleteFloor(@PathVariable String id) {
        return floorRepository.deleteById(id);
    }


}
