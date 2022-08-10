package com.example.farmingproject.jpql;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class HighestCustomer {

    private String name;
    private Double weight;
    private BigDecimal price;
}
