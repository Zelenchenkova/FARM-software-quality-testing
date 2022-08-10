package com.example.farmingproject.repository;

import com.example.farmingproject.domain.CropWorkTech;
import org.springframework.data.repository.CrudRepository;

public interface CropWorkTechRepository extends CrudRepository<CropWorkTech, Integer> {

    Long countById(int id);
}
