package com.example.demo.access;

import com.example.demo.level.Level;
import com.example.demo.personal.Personal;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String access;

    @ManyToMany
    @JoinTable(
            name="personal_registered",
            joinColumns = @JoinColumn(name = "access_id"),
            inverseJoinColumns = @JoinColumn(name = "personal_id")
    )
    private Set<Personal> registeredPersonal = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level connectedLevel;



    public Long getId() {return id;}

    public String getAccess() {return access;}

    public void setAccess(String access) {this.access = access;}

    public Set<Personal> getRegisteredPersonal() {
        return registeredPersonal;
    }

    public void registerPersonal(Personal personal) {
        registeredPersonal.add(personal);
    }

    public Level getConnectedLevel() {
        return connectedLevel;
    }

    public void connectLevel(Level level) {
        this.connectedLevel = level;
    }
}
