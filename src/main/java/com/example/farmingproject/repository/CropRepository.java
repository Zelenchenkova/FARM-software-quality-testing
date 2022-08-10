package com.example.farmingproject.repository;

import com.example.farmingproject.domain.Crop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface CropRepository extends CrudRepository<Crop, Integer> {

    Long countById(int id);

    // Які посіви відбулися з вказаної дати по вказану дату?
    @Query(value = "SELECT * FROM crop" +
            " WHERE date BETWEEN ?1" +
            " AND ?2 ORDER BY date", nativeQuery = true)
    List<Crop> findCropsByDate(Date startDate, Date endDate);
}
