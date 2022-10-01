package com.example.farmingproject.service;

import com.example.farmingproject.entities.FertilizerType;
import com.example.farmingproject.jpql.MostExpensiveFertilizerForTypes;
import com.example.farmingproject.repository.FertilizerTypeRepository;
import com.example.farmingproject.util.MostExpensiveForTypesPDFExporter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FertilizerTypeService implements GeneralContent{

    private final FertilizerTypeRepository fertilizerTypeRepository;

    public List<FertilizerType> findAllFertilizerTypes() {
        return (List<FertilizerType>) fertilizerTypeRepository.findAll();
    }

    public FertilizerType findFertilizerTypeById(Integer id) {
        try {
            Optional<FertilizerType> result = fertilizerTypeRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any fertilizer type with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveFertilizerType(FertilizerType fertilizerType) {
        fertilizerTypeRepository.save(fertilizerType);
    }

    public void deleteFertilizerType(Integer id) throws EntityNotFoundException {
        Long count = fertilizerTypeRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any fertilizer type with id " + id);
        }
        fertilizerTypeRepository.deleteById(id);
    }

    public void exportToPDFFertilizerType(HttpServletResponse response) throws IOException {
        setPdfParams(response, "fertilizerTypes");
        new MostExpensiveForTypesPDFExporter(perform()).export(response);
    }

    public Set<MostExpensiveFertilizerForTypes> findMostExpensiveFertilizerForTypes() {
        return perform();
    }

    private Set<MostExpensiveFertilizerForTypes> perform() {
        Set<MostExpensiveFertilizerForTypes> set = new HashSet<>();
        return fertilizerTypeRepository.findMostExpensiveFertilizerForTypes()
                .stream().flatMap(row -> {
                    set.add(new MostExpensiveFertilizerForTypes(
                            (String) row[0],
                            (String) row[1]
                    ));
                    return set.stream();
                }).collect(Collectors.toSet());
    }
}
