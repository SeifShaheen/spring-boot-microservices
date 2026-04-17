package com.example.ratingsservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "userid")
    private String userId;

    @Column(name = "movieid")
    private String movieId;
    private int rating;

    public Rating() {}

    public Rating(String userId, String movieId, int rating) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
    }

    public Long getId() { return id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}