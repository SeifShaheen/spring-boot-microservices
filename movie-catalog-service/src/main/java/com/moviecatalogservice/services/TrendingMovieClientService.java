package com.moviecatalogservice.services;

import com.example.trendingmovieservice.Empty;
import com.example.trendingmovieservice.Movie;
import com.example.trendingmovieservice.MovieList;
import com.example.trendingmovieservice.TrendingServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendingMovieClientService {

    @GrpcClient("trending-service")
    private TrendingServiceGrpc.TrendingServiceBlockingStub trendingStub;

    public List<Movie> getTop10Movies() {
        MovieList response = trendingStub.getTop10Movies(Empty.newBuilder().build());
        return response.getMoviesList();
    }
}