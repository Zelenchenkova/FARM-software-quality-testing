package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

// Врожай-Реалізація
@Entity
@Table(name = "harvest_sale")
@Getter
@Setter
@EqualsAndHashCode
public class HarvestSale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_harvest", referencedColumnName = "id")
    private Harvest harvest;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_sale", referencedColumnName = "id")
    private Sale sale;
}
