package com.example.farmingproject.service;

import com.example.farmingproject.domain.CropWorkTech;
import com.example.farmingproject.repository.CropWorkTechRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CropWorkTechService {

    private final CropWorkTechRepository cropWorkTechRepository;

    public List<CropWorkTech> findAllCropWorkTeches() {
        return (List<CropWorkTech>) cropWorkTechRepository.findAll();
    }

    public CropWorkTech findCropWorkTechById(Integer id) {
        try {
            Optional<CropWorkTech> result = cropWorkTechRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any crop-work-tech with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveCropWorkTech(CropWorkTech cropWorkTech) {
        cropWorkTechRepository.save(cropWorkTech);
    }

    public void deleteCropWorkTech(Integer id) throws EntityNotFoundException {
        Long count = cropWorkTechRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any crop-work-tech with id " + id);
        }
        cropWorkTechRepository.deleteById(id);
    }
}
