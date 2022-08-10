package com.example.farmingproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "work")
@Getter
@Setter
@EqualsAndHashCode(exclude = "cropWorks")
public class Work implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tech", referencedColumnName = "id")
    private TechType techTypeW;

    @OneToMany(mappedBy = "work", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CropWork> cropWorks;
}
