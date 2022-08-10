package com.example.farmingproject.repository;

import com.example.farmingproject.domain.TechType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface TechTypeRepository extends CrudRepository<TechType, Integer> {

    Long countById(int id);

    // Якого року в середньому техніка для кожного типу?
    @Query(value = "SELECT tech_type.name, AVG(tech.year)" +
            " FROM tech_type " +
            "JOIN tech ON tech_type.id = tech.id_ttype" +
            " GROUP BY tech_type.name" +
            " ORDER BY tech_type.name", nativeQuery = true)
    Set<Object[]> findAvgTechYearByType();
}
