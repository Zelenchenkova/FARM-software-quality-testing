package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Добриво
@Entity
@Table(name = "fertilizer")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"cropFertilizers", "providerFertilizers"})
public class Fertilizer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "addInfo")
    private String addInfo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_ftype", referencedColumnName = "id")
    private FertilizerType fertilizerType;

    @OneToMany(mappedBy = "fertilizer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CropFertilizer> cropFertilizers;

    @OneToMany(mappedBy = "fertilizer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProviderFertilizer> providerFertilizers;
}
