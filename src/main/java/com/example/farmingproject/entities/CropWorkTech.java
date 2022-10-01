package com.example.farmingproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

// Техніка-Посів-Робота
@Entity
@Table(name = "crop_work_tech")
@Getter
@Setter
@EqualsAndHashCode
public class CropWorkTech implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_crop_work",referencedColumnName = "id")
    private CropWork cropWork;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tech", referencedColumnName = "id")
    private Tech tech;
}
