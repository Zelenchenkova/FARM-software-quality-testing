package com.example.farmingproject.service;

import com.example.farmingproject.domain.ProviderCulture;
import com.example.farmingproject.repository.ProviderCultureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProviderCultureService {

    private final ProviderCultureRepository providerCultureRepository;

    public List<ProviderCulture> findAllProviderCultures() {
        return (List<ProviderCulture>) providerCultureRepository.findAll();
    }

    public ProviderCulture findProviderCultureById(Integer id) {
        try {
            Optional<ProviderCulture> result = providerCultureRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any provider-culture with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveProviderCulture(ProviderCulture providerCulture) {
        providerCultureRepository.save(providerCulture);
    }

    public void deleteProviderCulture(Integer id) throws EntityNotFoundException {
        Long count = providerCultureRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any provider-culture with id " + id);
        }
        providerCultureRepository.deleteById(id);
    }
}
