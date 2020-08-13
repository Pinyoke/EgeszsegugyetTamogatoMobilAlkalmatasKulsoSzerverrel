package com.springserver.server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Allergens")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Allergen extends BaseEntity{

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "allergen", cascade = CascadeType.DETACH)
    private List<Sensitivity> sensitivitys;

    public Allergen() {
    }

    public Allergen(String name, List<Sensitivity> sensitivitys) {
        this.name = name;
        this.sensitivitys = sensitivitys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sensitivity> getSensitivitys() {
        return sensitivitys;
    }

    public void setSensitivitys(List<Sensitivity> sensitivitys) {
        this.sensitivitys = sensitivitys;
    }
}
