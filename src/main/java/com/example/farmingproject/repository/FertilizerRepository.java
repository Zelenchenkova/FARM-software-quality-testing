package com.example.farmingproject.repository;


import com.example.farmingproject.domain.Fertilizer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface FertilizerRepository extends CrudRepository<Fertilizer, Integer> {

    Long countById(int id);

    // TODO: можна покращити
    // Які є органічні добрива? Відсортувати за назвою.
    @Query(value = "SELECT * FROM fertilizer" +
            " WHERE id_ftype =" +
            " (SELECT id FROM fertilizer_type" +
            " WHERE name = 'organic')" +
            " ORDER BY name", nativeQuery = true)
    List<Fertilizer> findOrganicFertilizers();

    // Яке добриво не використовувалося на посівах?
    @Query(value = "SELECT * FROM fertilizer" +
            " WHERE NOT EXISTS" +
            " (SELECT id_fertilizer FROM crop_fertilizer" +
            " WHERE crop_fertilizer.id_fertilizer = fertilizer.id)", nativeQuery = true)
    List<Fertilizer> findUnusedFertilizers();

    // Вивести назви товарів, що замовлялися у вказаного постачальника
    @Query(value = "SELECT fertilizer.name, 'fertilizer' AS COMMENT" +
            " FROM fertilizer" +
            " JOIN provider_fertilizer ON fertilizer.id = provider_fertilizer.id_fertilizer" +
            " WHERE kod = (SELECT kod FROM provider WHERE name = ?1) \n" +
            "UNION \n" +
            "SELECT culture.name, 'culture' AS COMMENT" +
            " FROM culture" +
            " JOIN provider_culture ON culture.id = provider_culture.id_culture" +
            " WHERE kod = (SELECT kod FROM provider WHERE name = ?1) \n" +
            "ORDER BY name", nativeQuery = true)
    Set<Object[]> findFertsNamesByProvider(String name);

    // Дописати в додаткову інформацію "most popular" до найбільш часто використовуваного добрива
    @Modifying
    @Query(value = "SET SQL_SAFE_UPDATES = 0;\n" +
            "UPDATE fertilizer SET addInfo = 'most popular' \n" +
            "WHERE id IN " +
            "(SELECT  crop_fertilizer.id_fertilizer FROM  crop_fertilizer" +
            " GROUP BY  crop_fertilizer.id_fertilizer \n" +
            "HAVING COUNT(*) >=" +
            " (SELECT max(my_count) FROM(SELECT COUNT(*) as my_count" +
            " FROM  crop_fertilizer" +
            " GROUP BY  crop_fertilizer.id_fertilizer)AS A));\n" +
            "SET SQL_SAFE_UPDATES = 1", nativeQuery = true)
    void setColumnMostPopularFertilizer();
}
