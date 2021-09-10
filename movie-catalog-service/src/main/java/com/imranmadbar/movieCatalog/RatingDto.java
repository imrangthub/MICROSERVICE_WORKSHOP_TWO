package com.imranmadbar.movieCatalog;

public class RatingDto {
	
    public RatingDto() {}
	
    private String movieId;
    private int rating;

    public RatingDto(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    

}
