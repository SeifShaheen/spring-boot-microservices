package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import com.example.movieinfoservice.service.MovieService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final MovieService movieService;

    public MovieResource(RestTemplate restTemplate, MovieService movieService) {
        this.restTemplate = restTemplate;
        this.movieService = movieService;
    }

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {

        Movie movie = movieService.getMovieByMovieId(movieId);

        if (movie != null) {
            System.out.println("FROM CACHE ");
            return movie;
        }


        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;

        MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);


        Movie newMovie = new Movie(
                movieId,
                movieSummary.getTitle(),
                movieSummary.getOverview(),
                new Date()
        );


        movieService.saveMovie(
                newMovie.getMovieId(),
                newMovie.getName(),
                newMovie.getDescription()
        );

        System.out.println("FROM API ");

        return newMovie;
    }}