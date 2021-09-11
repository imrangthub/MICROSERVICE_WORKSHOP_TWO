package com.imranmadbar.movieCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfoService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	@HystrixCommand(
			fallbackMethod="getFallBackMovieDto",
			threadPoolKey = "movieInfoPool",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "20"),
					@HystrixProperty(name = "maxQueueSize", value = "10")
			  }
			)
	public MovieDto getMovie(RatingDto ratingObj) {
		return restTemplate.getForObject("http://movie-info-service/movies/" + ratingObj.getMovieId(),
				MovieDto.class);
	}
	
	
	//=================================================
	
	@HystrixCommand(fallbackMethod="getFallBackMovieDto")
	public MovieDto getMovieDto(RatingDto ratingObj) {
		return restTemplate.getForObject("http://movie-info-service/movies/" + ratingObj.getMovieId(),
				MovieDto.class);
	}
	
	// 3
	public MovieDto getFallBackMovieDto(RatingDto ratingObj) { 
		System.out.println("#######################  Msg Form getFallBackMovieDto ##############################");
		return new MovieDto("No Id", "No Name",00 );
	}
	
	

}
