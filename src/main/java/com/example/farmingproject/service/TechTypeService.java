package com.example.farmingproject.service;

import com.example.farmingproject.domain.TechType;
import com.example.farmingproject.jpql.AvgTechYearByType;
import com.example.farmingproject.repository.TechTypeRepository;
import com.example.farmingproject.util.AvgTechYearPDFExporter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TechTypeService implements GeneralContent{

    private final TechTypeRepository techTypeRepository;

    public List<TechType> findAllTechTypes() {
        return (List<TechType>) techTypeRepository.findAll();
    }

    public TechType findTechTypeById(Integer id) {
        try {
            Optional<TechType> result = techTypeRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any tech-type with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveTechType(TechType techType) {
        techTypeRepository.save(techType);
    }

    public void deleteTechType(Integer id) throws EntityNotFoundException {
        Long count = techTypeRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any tech-type with id " + id);
        }
        techTypeRepository.deleteById(id);
    }

    public void exportToPDFTechType(HttpServletResponse response)  throws IOException {
        setPdfParams(response, "techTypes");
        new AvgTechYearPDFExporter(perform()).export(response);
    }

    public Set<AvgTechYearByType> findTheAvgTechYearByType() {
        return perform();
    }

    private Set<AvgTechYearByType> perform() {
        Set<AvgTechYearByType> set = new HashSet<>();
        return techTypeRepository.findAvgTechYearByType()
                .stream().flatMap(row -> {
                    BigDecimal r = (BigDecimal) row[1];
                    set.add(new AvgTechYearByType(
                            (String) row[0],
                            r.intValue()
                    ));
                    return set.stream();
                }).collect(Collectors.toSet());
    }
}
