package com.example.farmingproject.jpql;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class SumByDate {

    private Date date;
    private BigDecimal sum;
}
