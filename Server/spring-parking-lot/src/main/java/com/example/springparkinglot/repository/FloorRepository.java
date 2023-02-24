package com.example.springparkinglot.repository;

import com.example.springparkinglot.models.Floor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface FloorRepository extends ReactiveMongoRepository<Floor, String> {

}
