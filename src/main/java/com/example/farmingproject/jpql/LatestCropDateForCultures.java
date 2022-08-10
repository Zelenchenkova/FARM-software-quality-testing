package com.example.farmingproject.jpql;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class LatestCropDateForCultures {

    private String name;
    private Date date;
}
