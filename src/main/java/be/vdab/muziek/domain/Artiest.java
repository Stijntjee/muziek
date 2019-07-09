package be.vdab.muziek.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artiesten")
public class Artiest implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;

    //CONSTRUCTORS
    protected Artiest() {
    }

    public Artiest(String naam) {
        this.naam = naam;
    }

    //GETTERS
    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
