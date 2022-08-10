package com.example.farmingproject.service;

import com.example.farmingproject.domain.CropFertilizer;
import com.example.farmingproject.repository.CropFertilizerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CropFertilizerService {

    private final CropFertilizerRepository cropFertilizerRepository;

    public List<CropFertilizer> findAllCropFertilizers() {
        return (List<CropFertilizer>) cropFertilizerRepository.findAll();
    }

    public CropFertilizer findCropFertilizerById(Integer id) {
        try {
            Optional<CropFertilizer> result = cropFertilizerRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any crop-fertilizer with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveCropFertilizer(CropFertilizer cropFertilizer) {
        cropFertilizerRepository.save(cropFertilizer);
    }

    public void deleteCropFertilizer(Integer id) throws EntityNotFoundException {
        Long count = cropFertilizerRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any crop-fertilizer with id " + id);
        }
        cropFertilizerRepository.deleteById(id);
    }
}
