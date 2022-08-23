package com.example.farmingproject.service;

import com.example.farmingproject.domain.Sale;
import com.example.farmingproject.jpql.HighestCustomer;
import com.example.farmingproject.repository.SaleRepository;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public List<Sale> findAllSales() {
        return (List<Sale>) saleRepository.findAll();
    }

    public Sale findSaleById(Integer id) {
        try {
            Optional<Sale> result= saleRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any sale with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveSale(Sale sale) {
        saleRepository.save(sale);
    }

    public void deleteSale(Integer id) throws EntityNotFoundException {
        Long count = saleRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any sale with id " + id);
        }
        saleRepository.deleteById(id);
    }

    public Set<HighestCustomer> findTheHighestCustomer() {
        Set<HighestCustomer> set = new HashSet<>();
        return saleRepository.findCustomerSpentTheMost()
            .stream().flatMap(row -> {
                set.add(new HighestCustomer(
                    (String) row[0],
                    (Double) row[1],
                    BigDecimal.valueOf((Double) row[2])));
                return set.stream();
            }).collect(Collectors.toSet());
    }
}
