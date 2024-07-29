package org.example;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(schema = "movie", name = "actor")
public class Actor {
    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "first_name", columnDefinition = "varchar(45)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "varchar(45)")
    private String lastName;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    private Set<Film> films = new HashSet<Film>();
}
