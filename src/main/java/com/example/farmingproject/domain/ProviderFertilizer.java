package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

// Добриво-Постачальник
@Entity
@Table(name = "provider_fertilizer")
@Getter
@Setter
@EqualsAndHashCode
public class ProviderFertilizer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_fertilizer", referencedColumnName = "id")
    private Fertilizer fertilizer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "kod", referencedColumnName = "kod")
    private Provider provider;
}
