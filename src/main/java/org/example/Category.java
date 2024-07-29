package org.example;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(schema = "movie", name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(columnDefinition = "varchar(25)")
    private String name;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    private Set<Film> films = new HashSet<Film>();
}
