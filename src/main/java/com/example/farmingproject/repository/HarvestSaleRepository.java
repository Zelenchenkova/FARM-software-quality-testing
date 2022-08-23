package com.example.farmingproject.repository;

import com.example.farmingproject.domain.HarvestSale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface HarvestSaleRepository extends CrudRepository<HarvestSale, Integer> {

    Long countById(int id);

    // На яку суму було продано всі врожаї?
    @Query(value = "SELECT SUM(weight*price)" +
            "FROM harvest_sale", nativeQuery = true)
    Double findTotalSaleSum();
}
