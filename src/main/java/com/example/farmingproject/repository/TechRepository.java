package com.example.farmingproject.repository;

import com.example.farmingproject.domain.Tech;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TechRepository extends CrudRepository<Tech, Integer> {

    Long countById(int id);

    // TODO: Можна покращити
    // Техніка для поливу відсортована за назвою
    @Query(value = "SELECT * FROM tech" +
            " WHERE id_ttype =" +
            "(SELECT id FROM tech_type" +
            " WHERE name = 'Watering and irrigation equipment')" +
            " ORDER BY name", nativeQuery = true)
    List<Tech> findWateringTech();

    // Техніка, випущена з вказаного року до вказаного року
    @Query(value = "SELECT * FROM tech " +
            "WHERE year BETWEEN ?1 AND ?2" +
            " ORDER BY year", nativeQuery = true)
    List<Tech> findTechByYear(Integer yearE, Integer yearL);

    // Якого року випуску найновіша наявна техніка та її назва?
    @Query(value = "SELECT new com.example.farmingproject.domain.Tech(t.name, t.year) FROM Tech AS t" +
            " WHERE t.year >= ALL(SELECT year FROM Tech)")
    List<Tech> findNewestTech();
}
