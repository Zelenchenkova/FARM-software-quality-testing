package com.example.farmingproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Постачальник
@Entity
@Table(name = "provider")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"providerFertilizers", "providerCultures"})
public class Provider implements Serializable {

    @Id
    @Column(name = "kod")
    private Integer kod;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProviderFertilizer> providerFertilizers;

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProviderCulture> providerCultures;
}
