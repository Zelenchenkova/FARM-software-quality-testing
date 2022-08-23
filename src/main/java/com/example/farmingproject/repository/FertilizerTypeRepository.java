package com.example.farmingproject.repository;

import com.example.farmingproject.domain.FertilizerType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface FertilizerTypeRepository extends CrudRepository<FertilizerType, Integer> {

    Long countById(int id);

    // Для кожного типу добрива обрати найдорожче добриво
    @Query(value = "SELECT A.name, fertilizer.name AS B FROM fertilizer_type A\n" +
            "JOIN fertilizer ON A.id = fertilizer.id_ftype\n" +
            "WHERE fertilizer.id = (SELECT id_fertilizer FROM  provider_fertilizer WHERE price = \n" +
            "\t(\n" +
            "    SELECT MAX(price) FROM  provider_fertilizer" +
            " JOIN fertilizer ON provider_fertilizer.id_fertilizer = fertilizer.id \n" +
            "    WHERE fertilizer.id_ftype = A.id\n" +
            "    ))", nativeQuery = true)
    Set<Object[]> findMostExpensiveFertilizerForTypes();
}
