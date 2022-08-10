package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Посів-Робота
@Entity
@Table(name = "crop_work")
@Getter
@Setter
@EqualsAndHashCode(exclude = "cropWorkTeches")
public class CropWork implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_crop", referencedColumnName = "id")
    private Crop crop;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_work", referencedColumnName = "id")
    private Work work;

    @OneToMany(mappedBy = "cropWork", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CropWorkTech> cropWorkTeches;
}
