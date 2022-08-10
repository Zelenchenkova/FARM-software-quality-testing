package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

// Посів-Добриво
@Entity
@Table(name = "crop_fertilizer")
@Getter
@Setter
@EqualsAndHashCode
public class CropFertilizer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_crop", referencedColumnName = "id")
    private Crop crop;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_fertilizer", referencedColumnName = "id")
    private Fertilizer fertilizer;
}
