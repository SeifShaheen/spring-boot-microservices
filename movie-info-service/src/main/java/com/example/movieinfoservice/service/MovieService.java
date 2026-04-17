package com.example.movieinfoservice.service;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.repos.MovieRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovieService {

    private final MongoTemplate mongoTemplate;
    private final MovieRepository movieRepository;


    public MovieService(MongoTemplate mongoTemplate, MovieRepository movieRepository) {
        this.mongoTemplate = mongoTemplate;
        this.movieRepository = movieRepository;
    }


    public Movie saveMovie(String movieId, String name, String description) {
        Query query = Query.query(Criteria.where("movieId").is(movieId));
        Movie existingMovie = mongoTemplate.findOne(query, Movie.class);

        if (existingMovie != null) {
            existingMovie.setName(name);
            existingMovie.setDescription(description);
//            existingMovie.setAddedAt(new Date());
            return mongoTemplate.save(existingMovie);
        } else {
            Movie movie = new Movie(movieId, name, description, new Date());
            return movieRepository.save(movie);
        }
    }


    public Movie getMovieByMovieId(String movieId) {
        List<Movie> movies = mongoTemplate.find(
                Query.query(Criteria.where("movieId").is(movieId)),
                Movie.class
        );
        return movies.isEmpty() ? null : movies.get(0);
    }
}