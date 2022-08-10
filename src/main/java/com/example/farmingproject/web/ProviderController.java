package com.example.farmingproject.web;

import com.example.farmingproject.domain.Provider;
import com.example.farmingproject.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping(value = "/providers/byName", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public List<Provider> findProvByName(String name) {
        return providerService.findProviderByNameWith(name);
    }
}
