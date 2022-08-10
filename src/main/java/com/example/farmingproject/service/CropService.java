package com.example.farmingproject.service;

import com.example.farmingproject.domain.Crop;
import com.example.farmingproject.repository.CropRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CropService {

    private final CropRepository cropRepository;

    public List<Crop> findAllCrops() {
        return (List<Crop>) cropRepository.findAll();
    }

    public Crop findCropById(Integer id) {
        try {
            Optional<Crop> result = cropRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any crop with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveCrop(Crop crop) {
        cropRepository.save(crop);
    }

    public void deleteCrop(Integer id) throws EntityNotFoundException {
        Long count = cropRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any crop with id " + id);
        }
        cropRepository.deleteById(id);
    }

    public List<Crop> findCropsByDate(Date dateStart, Date dateEnd) {
        return cropRepository.findCropsByDate(dateStart, dateEnd);
    }
}
