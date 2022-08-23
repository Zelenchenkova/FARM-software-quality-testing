package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Культура
@Entity
@Table(name = "culture")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"providerCultures", "crops"})
@NoArgsConstructor
public class Culture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "addInfo")
    private String addInfo;

    @Column(name = "season")
    private String season;

    @OneToMany(mappedBy = "culture", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProviderCulture> providerCultures;

    @OneToMany(mappedBy = "culture", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Crop> crops;
}
