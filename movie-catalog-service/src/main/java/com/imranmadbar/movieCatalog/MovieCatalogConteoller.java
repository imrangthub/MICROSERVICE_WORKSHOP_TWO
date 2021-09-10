package com.imranmadbar.movieCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogConteoller {


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/list/{userId}")
    public List<CatalogItemDto> listOfMovieCatalogItem(@PathVariable("userId") String userId) {

        List<CatalogItemDto> catalogItemList = new ArrayList<CatalogItemDto>();

        UserRatingDto userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/foo", UserRatingDto.class);

        System.out.println("userRating res: " + userRating);

        for (RatingDto ratingObj : userRating.getUserRating()) {
            MovieDto movie = restTemplate.getForObject("http://localhost:8082/movies/" + ratingObj.getMovieId(), MovieDto.class);
            catalogItemList.add(new CatalogItemDto(movie.getName(), "Description", ratingObj.getRating()));
        }

        System.out.println("catalogItemList:" + catalogItemList);

        return catalogItemList;

    }





}
