package com.example.farmingproject.repository;

import com.example.farmingproject.domain.Sale;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<Sale, Integer> {

    Long countById(int id);

    // Який покупець витратив на покупку найбільше грошей?
    @Query(value = "SELECT customer, weight, price" +
            " FROM sale" +
            " JOIN harvest_sale ON sale.id = harvest_sale.id_sale" +
            " WHERE weight*price >=" +
            " ALL(SELECT weight*price FROM harvest_sale)",
            nativeQuery = true)
    Set<Object[]> findCustomerSpentTheMost();
}
