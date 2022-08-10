package com.example.farmingproject.repository;

import com.example.farmingproject.domain.Culture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface CultureRepository extends CrudRepository<Culture, Integer> {

    Long countById(int id);

    // Культура, чия назва починається на вказану
    @Query(value = "SELECT * FROM culture WHERE name RLIKE %1", nativeQuery = true)
    List<Culture> findCultureByName(String name);

    // Для кожної культури знайти найпізнішу дату посіву
    @Query(value = "SELECT A.name, crop.date FROM culture A\n" +
            "JOIN crop ON A.id = crop.id_culture\n" +
            "WHERE crop.id = (SELECT crop.id FROM  crop WHERE crop.date = \n" +
            "\t(\n" +
            "    SELECT date FROM crop \n" +
            "    WHERE crop.id_culture = A.id\n" +
            "    ORDER BY date DESC LIMIT 1\n" +
            "    ))", nativeQuery = true)
    Set<Object[]> findLatestCropDateForCultures();

    // TODO: check
    // Яку культуру не садили?
    @Query(value = "SELECT culture.name FROM culture" +
            " LEFT JOIN crop ON culture.id = crop.id_culture" +
            " WHERE crop.id_culture IS NULL", nativeQuery = true)
    List<Culture> findUnusedCultures();

    // Дописати в додаткову інформацію "most popular" до найбільш часто засіюваної культури
    @Modifying
    @Query(value = "SET SQL_SAFE_UPDATES = 0;\n" +
            "UPDATE culture SET addInfo = 'most popular' \n" +
            "WHERE culture.id IN" +
            " (SELECT  crop.id_culture FROM  crop" +
            " GROUP BY  crop.id_culture \n" +
            "HAVING COUNT(*) >=" +
            " (SELECT max(my_count) FROM(SELECT COUNT(*) as my_count" +
            " FROM crop GROUP BY  crop.id_culture) AS A));\n" +
            "SET SQL_SAFE_UPDATES = 1", nativeQuery = true)
    void setColumnMostPopularCulture();
}
