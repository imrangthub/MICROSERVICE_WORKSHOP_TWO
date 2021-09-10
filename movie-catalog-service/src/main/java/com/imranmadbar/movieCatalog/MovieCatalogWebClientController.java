package com.imranmadbar.movieCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/catalog2")
public class MovieCatalogWebClientController {


    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItemDto> getCatalogsList(@PathVariable("userId") String userId) {

        List<RatingDto> ratings = Arrays.asList(
                new RatingDto("1111", 4),
                new RatingDto("2222", 5)
        );
        return ratings.stream()
                .map(rating -> {
                    // MovieDto movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), MovieDto.class);

                    MovieDto movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/movies/"+rating.getMovieId())
                            .retrieve()
                            .bodyToMono(MovieDto.class)
                            .block();

                    return new CatalogItemDto(movie.getName(), "Description with Web Client", rating.getRating());
                })
                .collect(Collectors.toList());

    }




}
