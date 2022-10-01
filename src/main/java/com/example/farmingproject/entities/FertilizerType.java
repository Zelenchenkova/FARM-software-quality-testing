package com.example.farmingproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Тип добрива
@Entity
@Table(name = "fertilizer_type")
@Getter
@Setter
@EqualsAndHashCode(exclude = "fertilizers")
public class FertilizerType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "fertilizerType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Fertilizer> fertilizers;
}
