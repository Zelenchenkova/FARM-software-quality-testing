package com.example.farmingproject.service;

import com.example.farmingproject.domain.Work;
import com.example.farmingproject.repository.WorkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;

    public List<Work> findAllWorks() {
        return (List<Work>) workRepository.findAll();
    }

    public Work findWorkById(Integer id) {
        try {
            Optional<Work> result = workRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any work with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveWork(Work work) {
        workRepository.save(work);
    }

    public void deleteWork(Integer id) throws EntityNotFoundException {
        Long count = workRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any work with id " + id);
        }
        workRepository.deleteById(id);
    }
}
