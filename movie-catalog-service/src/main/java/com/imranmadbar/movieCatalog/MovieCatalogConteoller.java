package com.imranmadbar.movieCatalog;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogConteoller {


    @Autowired
    private RestTemplate restTemplate;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());



//	@Autowired
//	private MovieInfoService movieInfoService;
//
//	@Autowired
//	private UserRatingInfoService userRatingInfoService;


    @RequestMapping("/list")
    public List<UserRatingDto> listOfRating() {
        LOG.info("Start calling ratingsdata service");
        List<UserRatingDto> userRatingList = null;
        userRatingList = restTemplate.getForObject("http://ratings-data-service/ratingsdata/list",
                List.class);
        System.out.println("userRatingDtoRes:" + userRatingList);
        LOG.info("Deone calling ratingsdata service");
        return userRatingList;

    }

    @RequestMapping("/list/{userId}")
    public List<CatalogItemDto> getCatalog(@PathVariable("userId") String userId) {
        List<CatalogItemDto> catalogItemList = new ArrayList<CatalogItemDto>();

        List<UserRatingDto> userRatingLsist = getUserRating(userId);

        MovieDto movie = getMovieDto();
        catalogItemList.add(new CatalogItemDto(movie.getName(), "Description", movie.getRating()));


        System.out.println("catalogItemList Update:" + catalogItemList);
        return catalogItemList;

    }

    @HystrixCommand(fallbackMethod = "getFallBackMovieDto")
    public MovieDto getMovieDto() {
        return restTemplate.getForObject("http://movie-info-service/movies/" +"1009", MovieDto.class);
    }

    @HystrixCommand(fallbackMethod = "getFallBackUserRating")
    public List<UserRatingDto> getUserRating(String userId) {
        List<UserRatingDto> userRatingList = null;
        userRatingList = restTemplate.getForObject("http://ratings-data-service/ratingsdata/list",
                List.class);
        System.out.println("userRating Update res: " + userRatingList);
        return userRatingList;
    }

    // 1
    public List<CatalogItemDto> getFallBackCatalog(@PathVariable("userId") String userId) {
        System.out.println("#######################  Msg Form getFallBackCatalog ##############################");
        return Arrays.asList(new CatalogItemDto("No Movie found", "", 0));
    }

    // 2
    public UserRatingDto getFallBackUserRating() {
        System.out.println("#######################  Msg Form getFallBackUserRating ##############################");
        UserRatingDto userRating = new UserRatingDto();
        userRating.setUserRating(Arrays.asList(new RatingDto("0", 0)));
        return userRating;
    }

    // 3
    public MovieDto getFallBackMovieDto(RatingDto ratingObj) {
        System.out.println("#######################  Msg Form getFallBackMovieDto ##############################");
        return new MovieDto("No Id", "No Name", 00);
    }

    public List<CatalogItemDto> getFallBackCatalogOld(@PathVariable("userId") String userId) {
        return Arrays.asList(new CatalogItemDto("No Movie found", "", 0));
    }

//	@RequestMapping("/list/{userId}")
//	@HystrixCommand(fallbackMethod="getFallBackCatalog")
//	public List<CatalogItemDto> listOfMovieCatalogItem(@PathVariable("userId") String userId) {
//
//		List<CatalogItemDto> catalogItemList = new ArrayList<CatalogItemDto>();
//
//		UserRatingDto userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users2/" + userId,
//				UserRatingDto.class);
//
//		System.out.println("userRating res: " + userRating);
//
//		for (RatingDto ratingObj : userRating.getUserRating()) {
//			MovieDto movie = restTemplate.getForObject("http://movie-info-service/movies/" + ratingObj.getMovieId(),
//					MovieDto.class);
//
//			catalogItemList.add(new CatalogItemDto(movie.getName(), "Description", ratingObj.getRating()));
//		}
//
//		System.out.println("catalogItemList:" + catalogItemList);
//
//		return catalogItemList;
//
//	}
//

    @RequestMapping("/{userId}")
    public List<CatalogItemDto> getCatalogOld(@PathVariable("userId") String userId) {

        List<RatingDto> ratingsList2 = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/foo",
                List.class);

        System.out.println("ratings res: " + ratingsList2);

        List<RatingDto> ratingsList = Arrays.asList(new RatingDto("11111", 1), new RatingDto("22222", 2));

        return ratingsList.stream().map(rating -> {
            MovieDto movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(),
                    MovieDto.class);
            return new CatalogItemDto(movie.getName(), "Description", rating.getRating());
        }).collect(Collectors.toList());

    }

//    @RequestMapping("/list/{userId}")
//    public List<CatalogItemDto> listOfMovieCatalogItem(@PathVariable("userId") String userId) {
//
//        List<CatalogItemDto> catalogItemList = new ArrayList<CatalogItemDto>();
//
//        UserRatingDto userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/foo", UserRatingDto.class);
//
//        System.out.println("userRating res: " + userRating);
//
//        for (RatingDto ratingObj : userRating.getUserRating()) {
//            MovieDto movie = restTemplate.getForObject("http://localhost:8082/movies/" + ratingObj.getMovieId(), MovieDto.class);
//            catalogItemList.add(new CatalogItemDto(movie.getName(), "Description", ratingObj.getRating()));
//        }
//
//        System.out.println("catalogItemList:" + catalogItemList);
//
//        return catalogItemList;
//
//    }





}
