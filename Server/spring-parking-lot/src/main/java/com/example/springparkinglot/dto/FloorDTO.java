package com.example.springparkinglot.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FloorDTO {
    String id = UUID.randomUUID().toString();
    String floor;
}
