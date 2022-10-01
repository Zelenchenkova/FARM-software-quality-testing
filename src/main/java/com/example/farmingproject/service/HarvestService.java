package com.example.farmingproject.service;

import com.example.farmingproject.entities.Harvest;
import com.example.farmingproject.repository.HarvestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HarvestService {

    private final HarvestRepository harvestRepository;

    public List<Harvest> findAllHarvest() {
        return (List<Harvest>) harvestRepository.findAll();
    }

    public Harvest findHarvestById(Integer id) {
        try {
            Optional<Harvest> result= harvestRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any harvest with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveHarvest(Harvest harvest) {
        harvestRepository.save(harvest);
    }

    public void deleteHarvest(Integer id) throws EntityNotFoundException {
        Long count = harvestRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any harvest with id " + id);
        }
        harvestRepository.deleteById(id);
    }

    public Double findHarvestByWeight(Date dateStart, Date dateEnd) {
        return Math.round(harvestRepository.findHarvestByWeight(dateStart, dateEnd)*10.0)/10.0;
    }
}
