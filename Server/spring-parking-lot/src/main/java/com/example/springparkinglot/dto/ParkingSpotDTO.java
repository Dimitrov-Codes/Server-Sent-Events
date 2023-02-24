package com.example.springparkinglot.dto;

import lombok.Data;

@Data
public class ParkingSpotDTO {
    String floorId;
    String parkingSpotId;
    Boolean status;
}
