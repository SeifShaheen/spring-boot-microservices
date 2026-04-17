package com.example.trendingmovieservice;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Bean
    public ManagedChannel trendingChannel() {
        return ManagedChannelBuilder
                .forAddress("localhost", 9092)
                .usePlaintext()
                .build();
    }

    @Bean
    public TrendingServiceGrpc.TrendingServiceBlockingStub trendingServiceGrpcClient(ManagedChannel trendingChannel) {
        return TrendingServiceGrpc.newBlockingStub(trendingChannel);
    }
}