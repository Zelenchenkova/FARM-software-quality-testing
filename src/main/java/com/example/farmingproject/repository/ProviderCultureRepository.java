package com.example.farmingproject.repository;

import com.example.farmingproject.domain.ProviderCulture;
import org.springframework.data.repository.CrudRepository;

public interface ProviderCultureRepository extends CrudRepository<ProviderCulture, Integer> {

    Long countById(int id);
}
