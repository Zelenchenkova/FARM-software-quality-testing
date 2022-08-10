package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Техніка
@Entity
@Table(name = "tech")
@Getter
@Setter
@EqualsAndHashCode(exclude = "cropWorkTeches")
public class Tech implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_ttype", referencedColumnName = "id")
    private TechType techType;

    @OneToMany(mappedBy = "tech", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CropWorkTech> cropWorkTeches;
}
