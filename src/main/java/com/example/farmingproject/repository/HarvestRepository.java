package com.example.farmingproject.repository;

import com.example.farmingproject.domain.Harvest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface HarvestRepository extends CrudRepository<Harvest, Integer> {

    Long countById(int id);

    // Скільки тонн врожаю було зібрано з вказаної дати по вказану дату?
    @Query(value = "SELECT SUM(weight) " +
            "FROM harvest WHERE date " +
            "BETWEEN ?1 AND ?2", nativeQuery = true)
    Double findHarvestByWeight(Date dateStart, Date dateEnd);
}
