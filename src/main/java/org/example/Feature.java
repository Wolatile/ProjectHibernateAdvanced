package org.example;

public enum Feature {
    Trailers("Trailers"),
    Commentaries("Commentaries"),
    DeletedScenes("Deleted Scenes"),
    BehindTheScenes("Behind the Scenes");

    private final String value;

    Feature(String value) {
        this.value = value;
    }
}
