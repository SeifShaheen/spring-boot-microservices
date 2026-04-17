package com.example.trendingmovieservice;

import com.example.trendingmovieservice.TrendingServiceGrpc;
import com.example.trendingmovieservice.models.CatalogItem;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import com.example.trendingmovieservice.Movie;
import com.example.trendingmovieservice.MovieList;
import com.example.trendingmovieservice.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@GrpcService
public class TrendingServiceImpl extends TrendingServiceGrpc.TrendingServiceImplBase {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void getTop10Movies(Empty request,
            StreamObserver<MovieList> responseObserver) {

        java.util.List<CatalogItem> allItemsList = new java.util.concurrent.CopyOnWriteArrayList<>();


        java.util.stream.IntStream.rangeClosed(1, 1000).parallel().forEach(userId -> {
            try {
                CatalogItem[] items = restTemplate.getForObject("http://movie-catalog-service/catalog/" + userId,
                        CatalogItem[].class);
                if (items != null && items.length > 0) {

                    if (items[0].getRating() != 0) {
                        allItemsList.addAll(Arrays.asList(items));
                    }
                }
            } catch (Exception e) {

            }
        });

        List<Movie> allMovies;
        if (!allItemsList.isEmpty()) {
            java.util.Map<String, java.util.List<CatalogItem>> groupedMovies = allItemsList.stream()
                    .collect(Collectors.groupingBy(CatalogItem::getMovieId));

            allMovies = groupedMovies.entrySet().stream()
                    .map(entry -> {
                        String movieId = entry.getKey();
                        java.util.List<CatalogItem> movieItems = entry.getValue();

                        double averageRating = movieItems.stream()
                                .mapToDouble(CatalogItem::getRating)
                                .average()
                                .orElse(0.0);

                        return Movie.newBuilder()
                                .setMovieId(movieId)
                                .setName(movieItems.get(0).getName()) // Take the name from the first item
                                .setRating(averageRating)
                                .build();
                    })
                    .collect(Collectors.toList());
        } else {
            allMovies = List.of();
        }

        List<Movie> top10 = allMovies.stream()
                .sorted((a, b) -> Double.compare(b.getRating(), a.getRating()))
                .limit(10)
                .toList();

        MovieList response = MovieList.newBuilder()
                .addAllMovies(top10)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}