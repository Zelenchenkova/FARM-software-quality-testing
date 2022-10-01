package com.example.farmingproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Врожай
@Entity
@Table(name = "harvest")
@Getter
@Setter
@EqualsAndHashCode(exclude = "harvestSale")
public class Harvest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_crop", referencedColumnName = "id")
    private Crop crop;

    @OneToMany(mappedBy = "harvest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<HarvestSale> harvestSale;
}
