package com.example.farmingproject.repository;

import com.example.farmingproject.entities.CropWork;
import org.springframework.data.repository.CrudRepository;

public interface CropWorkRepository extends CrudRepository<CropWork, Integer> {

    Long countById(int id);
}
