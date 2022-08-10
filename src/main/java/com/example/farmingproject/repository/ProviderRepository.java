package com.example.farmingproject.repository;

import com.example.farmingproject.domain.Provider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ProviderRepository extends CrudRepository<Provider, Integer> {

    Long countByKod(int id);

    // Постачальник, чия назва починається на вказану
    @Query(value = "SELECT * FROM provider WHERE name RLIKE ?1", nativeQuery = true)
    List<Provider> findProviderByName(String name);

    // З якими постачальниками і покупцями співпрацює фермерське господарство?
    @Query(value = "SELECT provider.name, 'provider' AS COMMENT" +
            " FROM provider" +
            " UNION" +
            " SELECT sale.customer, 'customer' AS COMMENT" +
            " FROM sale ORDER BY name", nativeQuery = true)
    Set<Object[]> findAllCustomersAndProviders();
}
