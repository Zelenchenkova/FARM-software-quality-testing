package com.example.farmingproject.service;

import com.example.farmingproject.domain.Tech;
import com.example.farmingproject.repository.TechRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TechService {

    private final TechRepository techRepository;

    public List<Tech> findAllTeches() {
        return (List<Tech>) techRepository.findAll();
    }

    public Tech findTechById(Integer id) {
        try {
            Optional<Tech> result = techRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any tech with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveTech(Tech tech) {
        techRepository.save(tech);
    }

    public void deleteTech(Integer id) throws EntityNotFoundException {
        Long count = techRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any tech with id " + id);
        }
        techRepository.deleteById(id);
    }

    public List<Tech> findWateringTech(String name) {
        return techRepository.findWateringTech();
    }

    public List<Tech> findTechByYear(Integer yearE, Integer yearL) {
        return techRepository.findTechByYear(yearE, yearL);
    }

    public List<Tech> findNewestTech() {
        return techRepository.findNewestTech();
    }
}
