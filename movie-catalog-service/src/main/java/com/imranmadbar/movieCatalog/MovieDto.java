package com.imranmadbar.movieCatalog;

public class MovieDto {
	
    private String movieId;
    private String name;

    private int rating;

    public MovieDto(){}

    public MovieDto(String movieId, String name, int rating) {
        this.movieId = movieId;
        this.name = name;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "movieId='" + movieId + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
