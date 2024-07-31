package org.example;

import jakarta.persistence.*;

@Entity
@Table(schema = "movie", name = "film_text")
public class FilmText {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
