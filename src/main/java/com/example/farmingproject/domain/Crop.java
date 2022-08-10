package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Посів
@Entity
@Table(name = "crop")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"harvests", "cropWorks", "cropFertilizers"})
public class Crop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "square")
    private Double square;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_culture", referencedColumnName = "id")
    private Culture culture;

    @OneToMany(mappedBy = "crop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Harvest> harvests;

    @OneToMany(mappedBy = "crop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CropWork> cropWorks;

    @OneToMany(mappedBy = "crop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CropFertilizer> cropFertilizers;
}
