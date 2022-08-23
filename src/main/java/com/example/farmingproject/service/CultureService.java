package com.example.farmingproject.service;

import com.example.farmingproject.domain.Culture;
import com.example.farmingproject.jpql.LatestCropDateForCultures;
import com.example.farmingproject.repository.CultureRepository;
import com.example.farmingproject.util.LatestCropPDFExporter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CultureService implements GeneralContent{

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

    public List<String> findUnusedCultures() {
        return cultureRepository.findUnusedCultures().stream().map(String::valueOf).collect(Collectors.toList());
    }

    public void exportToPDFCulture(HttpServletResponse response) throws IOException {
        setPdfParams(response, "cultures");
        new LatestCropPDFExporter(perform()).export(response);
    }

    public Set<LatestCropDateForCultures> findLatestCropDateForCultures() {
        return perform();
    }

    @Transactional
    public void setColumnMostPopularCulture() {
        cultureRepository.setColumnMostPopularCulture();
    }

    private Set<LatestCropDateForCultures> perform() {
        Set<LatestCropDateForCultures> set = new HashSet<>();
        return cultureRepository.findLatestCropDateForCultures()
                .stream().flatMap(row -> {
                    set.add(new LatestCropDateForCultures(
                            (String) row[0],
                            (Date) row[1]
                    ));
                    return set.stream();
                }).collect(Collectors.toSet());
    }
}
