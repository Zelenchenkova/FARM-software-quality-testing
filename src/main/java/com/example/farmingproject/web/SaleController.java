package com.example.farmingproject.web;

import com.example.farmingproject.jpql.HighestCustomer;
import com.example.farmingproject.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class SaleController {

    private SaleService saleService;

    @GetMapping(value = "/api/findHighestCustomer")
    @ResponseStatus(HttpStatus.OK)
    public Set<HighestCustomer> findHighestCustomer() {
        return saleService.findTheHighestCustomer();
    }
}
