package com.example.farmingproject.service;

import com.example.farmingproject.entities.HarvestSale;
import com.example.farmingproject.repository.HarvestSaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

// Власник - Врожай
@Service
@AllArgsConstructor
public class HarvestSaleService {

    private final HarvestSaleRepository harvestSaleRepository;

    public List<HarvestSale> findAllHarvestSales() {
        return (List<HarvestSale>) harvestSaleRepository.findAll();
    }

    public HarvestSale findHarvestSaleById(Integer id) {
        try {
            Optional<HarvestSale> result = harvestSaleRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any harvest-sale with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void deleteHarvestSale(Integer id) throws EntityNotFoundException {
        Long count = harvestSaleRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any harvest-sale with id " + id);
        }
        harvestSaleRepository.deleteById(id);
    }

    public void saveHarvestSale(HarvestSale harvestSale) {
        harvestSaleRepository.save(harvestSale);
    }

    public Double findTotalSaleSum() {
        return Math.round(harvestSaleRepository.findTotalSaleSum() * 10.0) / 10.0;
    }
}
