package com.example.farmingproject.repository;

import com.example.farmingproject.entities.CropFertilizer;
import org.springframework.data.repository.CrudRepository;

public interface CropFertilizerRepository extends CrudRepository<CropFertilizer, Integer> {

    Long countById(int id);
}
