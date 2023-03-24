package com.example.demo.level;

import com.example.demo.access.Access;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;

    @JsonIgnore
    @OneToMany(mappedBy = "connectedLevel")
    private Set<Access> access = new HashSet<>();



    public long getId() {return id;}

    public String getLevel() {return level;}

    public void setLevel(String level) {this.level = level;}

    public Set<Access> getAccess() {
        return access;
    }
}
