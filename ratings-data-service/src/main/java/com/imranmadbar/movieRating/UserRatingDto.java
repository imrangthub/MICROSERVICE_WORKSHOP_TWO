package com.imranmadbar.movieRating;

import java.util.List;

public class UserRatingDto {
	
	public UserRatingDto() {}
	
	private List<RatingDto> userRating;

	public List<RatingDto> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<RatingDto> userRating) {
		this.userRating = userRating;
	}
	
	
	

}
