package com.imranmadbar.movieRating;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ratingsdata")
public class RatingController {

	@GetMapping("/{movieId}")
	public RatingDto getRating(@PathVariable("movieId") String movieId) {

		return new RatingDto(movieId, 4);

	}

	@GetMapping("/users/{userId}")
	public UserRatingDto userRating(@PathVariable("userId") String userId) {

		UserRatingDto userRatingDto = new UserRatingDto();

		List<RatingDto> ratingsList = Arrays.asList(
				new RatingDto("11111", 11),
				new RatingDto("22222", 22)
		);

		userRatingDto.setUserRating(ratingsList);

		return userRatingDto;

	}


	@GetMapping("/users2/{userId}")
	public List<RatingDto> getList(@PathVariable("userId") String userId) {
		List<RatingDto> ratingsList = Arrays.asList(
				new RatingDto("MOV_11111", 11),
				new RatingDto("MOV_22222", 22)
		);
		return ratingsList;
	}
	
	
	

}
