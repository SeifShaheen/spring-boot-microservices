package com.example.trendingmovieservice.dto;

public class MovieDTO {
    private String movieId;
    private String name;
    private double rating;

    public MovieDTO() {}
    public MovieDTO(String movieId, String name, double rating) {
        this.movieId = movieId;
        this.name = name;
        this.rating = rating;
    }

    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}