package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

// Тип-Техніки
@Entity
@Table(name = "tech_type")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"teches", "works"})
public class TechType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "techType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Tech> teches;

    @OneToMany(mappedBy = "techTypeW", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private  Set<Work> works;
}
