package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(schema = "movie", name = "film_actor")
public class FilmActor {
    private short actorId;
    private short filmId;

}
