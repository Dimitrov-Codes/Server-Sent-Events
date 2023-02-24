package com.example.springparkinglot.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ParkingSpot {
    @Id
    String id;
    Boolean status;
}
