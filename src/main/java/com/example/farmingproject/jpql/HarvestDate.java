package com.example.farmingproject.jpql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class HarvestDate {

    private Date dateStart, dateFinish;
}
