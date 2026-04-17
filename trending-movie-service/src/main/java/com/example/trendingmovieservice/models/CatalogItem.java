package com.example.trendingmovieservice.models;

public class CatalogItem {
    private String movieId;
    private String name;
    private String description;
    private int rating;

    public CatalogItem() {
    }

    public CatalogItem(String movieId, String name, String description, int rating) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
