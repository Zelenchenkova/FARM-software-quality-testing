package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

// Культура-Постачальник
@Entity
@Table(name = "provider_culture")
@Getter
@Setter
@EqualsAndHashCode
public class ProviderCulture implements Serializable {

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
    @JoinColumn(name = "id_culture", referencedColumnName = "id")
    private Culture culture;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "kod", referencedColumnName = "kod")
    private Provider provider;
}
