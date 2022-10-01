package com.example.farmingproject.repository;

import com.example.farmingproject.entities.ProviderCulture;
import org.springframework.data.repository.CrudRepository;

public interface ProviderCultureRepository extends CrudRepository<ProviderCulture, Integer> {

    Long countById(int id);
}
