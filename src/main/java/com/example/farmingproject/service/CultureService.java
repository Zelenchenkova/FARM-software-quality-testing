package com.example.farmingproject.service;

import com.example.farmingproject.domain.Culture;
import com.example.farmingproject.jpql.LatestCropDateForCultures;
import com.example.farmingproject.repository.CultureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CultureService {

    private final CultureRepository cultureRepository;

    public List<Culture> findAllCultures() {
        return (List<Culture>) cultureRepository.findAll();
    }

    public Culture findCultureById(Integer id) {
        try {
            Optional<Culture> result = cultureRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any culture with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveCulture(Culture culture) {
        cultureRepository.save(culture);
    }

    public void deleteCulture(Integer id) throws EntityNotFoundException {
        Long count = cultureRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any culture with id " + id);
        }
        cultureRepository.deleteById(id);
    }

    public List<Culture> findCultureByName(String name) {
        return cultureRepository.findCultureByName(name);
    }

    public List<Culture> findUnusedCultures() {
        return cultureRepository.findUnusedCultures();
    }

    public Set<LatestCropDateForCultures> findLatestCropDateForCultures() {
        Set<LatestCropDateForCultures> set = new HashSet<>();
        return cultureRepository.findLatestCropDateForCultures()
                .stream().flatMap(row -> {
                    set.add(new LatestCropDateForCultures(
                            (String) row[0],
                            Date.valueOf((String) row[1])
                    ));
                    return set.stream();
                }).collect(Collectors.toSet());
    }

    public void setColumnMostPopularCulture() {
        cultureRepository.setColumnMostPopularCulture();
    }
}
