package com.example.farmingproject.service;

import com.example.farmingproject.domain.ProviderFertilizer;
import com.example.farmingproject.jpql.SumByDate;
import com.example.farmingproject.repository.ProviderFertilizerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProviderFertilizerService {

    private final ProviderFertilizerRepository providerFertilizerRepository;

    public List<ProviderFertilizer> findAllProviderFertilizers() {
        return (List<ProviderFertilizer>) providerFertilizerRepository.findAll();
    }

    public ProviderFertilizer findProviderFertilizerById(Integer id) {
        try {
            Optional<ProviderFertilizer> result = providerFertilizerRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any provider-fertilizer with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveProviderFertilizer(ProviderFertilizer providerFertilizer) {
        providerFertilizerRepository.save(providerFertilizer);
    }

    public void deleteProviderFertilizer(Integer id) throws EntityNotFoundException {
        Long count = providerFertilizerRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any provider-fertilizer with id " + id);
        }
        providerFertilizerRepository.deleteById(id);
    }

    public Set<SumByDate> findSumByDate() {
        Set<SumByDate> set = new HashSet<>();
        return providerFertilizerRepository.findSumByDate()
                .stream().flatMap(row -> {
                    set.add(new SumByDate(
                            (Date) row[0],
                            BigDecimal.valueOf((Double) row[1])
                    ));
                    return set.stream();
                }).collect(Collectors.toSet());
    }
}
