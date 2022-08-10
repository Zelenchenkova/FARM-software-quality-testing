package com.example.farmingproject.service;

import com.example.farmingproject.domain.CropWork;
import com.example.farmingproject.repository.CropWorkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

// Це власник Посів-Техніки-Роботи
@Service
@AllArgsConstructor
public class CropWorkService {

    private final CropWorkRepository cropWorkRepository;

    public List<CropWork> findAllCropWorks() {
        return (List<CropWork>) cropWorkRepository.findAll();
    }

    public CropWork findCropWorkById(Integer id) {
        try {
            Optional<CropWork> result = cropWorkRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any crop-work with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveCropWork(CropWork cropWork) {
        cropWorkRepository.save(cropWork);
    }

    public void deleteCropWork(Integer id) throws EntityNotFoundException {
        Long count = cropWorkRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any crop-work with id " + id);
        }
        cropWorkRepository.deleteById(id);
    }
}
