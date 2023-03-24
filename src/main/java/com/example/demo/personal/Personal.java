package com.example.demo.personal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.demo.access.Access;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "registeredPersonal")
    private Set<Access> accesses = new HashSet<>();

    public Long getId() {return id;}

    public String getPersonalName() {return name;}

    public void setPersonalName(String name) {this.name = name;}

    public Set<Access> getAccesses() {
        return accesses;
    }

    public void setAccesses(Set<Access> accesses) {
        this.accesses = accesses;
    }
}
