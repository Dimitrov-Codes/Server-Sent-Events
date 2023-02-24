package com.example.springparkinglot.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Floor {
    @Id
    String id = UUID.randomUUID().toString();
    @EqualsAndHashCode.Include
    String floor;
    List<ParkingSpot> parkingData = new ArrayList<>();
}
