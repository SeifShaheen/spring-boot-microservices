package com.moviecatalogservice.resources;

import com.example.trendingmovieservice.dto.MovieDTO;
import com.example.trendingmovieservice.TrendingServiceGrpc;
import com.example.trendingmovieservice.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TrendingMovieResource {

    @Autowired
    private final TrendingServiceGrpc.TrendingServiceBlockingStub trendingServiceGrpcClient;

    public TrendingMovieResource(TrendingServiceGrpc.TrendingServiceBlockingStub trendingServiceGrpcClient) {
        this.trendingServiceGrpcClient = trendingServiceGrpcClient;
    }

    @GetMapping("/trending")
    public List<MovieDTO> getTrendingMovies() {
        var movieListResponse = trendingServiceGrpcClient.getTop10Movies(Empty.newBuilder().build());
        return movieListResponse.getMoviesList()
                .stream()
                .map(m -> new MovieDTO(m.getMovieId(), m.getName(), m.getRating()))
                .collect(Collectors.toList());
    }
}