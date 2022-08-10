package com.example.farmingproject.repository;

import com.example.farmingproject.domain.ProviderFertilizer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProviderFertilizerRepository extends CrudRepository<ProviderFertilizer, Integer> {

    Long countById(int id);

    // Які суми було витрачено на закупівлю добрива кожного дня закупки?
    @Query(value = "SELECT date, " +
            "SUM(price) FROM provider_fertilizer" +
            " GROUP BY date", nativeQuery = true)
    Set<Object[]> findSumByDate();
}
