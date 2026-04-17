package com.example.movieinfoservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "movies")
public class Movie {
    @Id
    private String id;
    private String movieId;
    private String name;
    private String description;
    private Date cachedAt;

    // Constructors
    public Movie() {}
    public Movie(String movieId, String name, String description, Date cachedAt) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
        this.cachedAt = cachedAt;
    }
    public Movie(String movieId, String name, String description) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
    }
    // Getters & Setters
    public String getId() { return id; }
    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getCachedAt() { return cachedAt; }
    public void setCachedAt(Date cachedAt) { this.cachedAt = cachedAt; }
}
