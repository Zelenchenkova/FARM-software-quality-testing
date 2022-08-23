package com.example.farmingproject.service;

import com.example.farmingproject.domain.Provider;
import com.example.farmingproject.jpql.CustomersAndProviders;
import com.example.farmingproject.repository.ProviderRepository;
import com.example.farmingproject.util.PartnersPDFExporter;
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
public class ProviderService implements GeneralContent{

    private final ProviderRepository providerRepository;

    public List<Provider> findAllProviders() {
        return (List<Provider>) providerRepository.findAll();
    }

    public Provider findProviderById(Integer id) {
        try {
            Optional<Provider> result = providerRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any provider with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveProvider(Provider provider) {
        providerRepository.save(provider);
    }

    public void deleteProvider(Integer id) throws EntityNotFoundException {
        Long count = providerRepository.countByKod(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any provider with id " + id);
        }
        providerRepository.deleteById(id);
    }

    public void exportToPDFProvider(HttpServletResponse response) throws IOException {
        setPdfParams(response, "providers");
        new PartnersPDFExporter(perform()).export(response);
    }

    public List<Provider> findProviderByName(String name) {
        return providerRepository.findProviderByName(name);
    }

    public Set<CustomersAndProviders> findAllCustomersAndProviders() {
        return perform();
    }

    private Set<CustomersAndProviders> perform() {
        Set<CustomersAndProviders> set = new HashSet<>();
        return providerRepository.findAllCustomersAndProviders()
                .stream().flatMap(row -> {
                    set.add(new CustomersAndProviders(
                            (String) row[0],
                            (String) row[1]
                    ));
                    return set.stream();
                }).collect(Collectors.toSet());
    }
}
