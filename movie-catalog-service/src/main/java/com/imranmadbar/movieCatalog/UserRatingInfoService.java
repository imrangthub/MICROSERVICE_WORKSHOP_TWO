package com.imranmadbar.movieCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserRatingInfoService {

	@Autowired
	private RestTemplate restTemplate;

//	@HystrixCommand(fallbackMethod="getFallBackUserRating")
//	public UserRatingDto getUserRating(String userId) {
//		UserRatingDto userRatingDto = null;
//		userRatingDto =  restTemplate.getForObject("http://ratings-data-service/ratingsdata/users2/" + userId,
//				UserRatingDto.class);
//		System.out.println("userRating Update res: " + userRatingDto);
//		 return userRatingDto;
//	}
//	
//	
//	// 2
//	public UserRatingDto getFallBackUserRating() {
//		System.out.println("#######################  Msg Form getFallBackUserRating ##############################");
//		UserRatingDto userRating = new UserRatingDto();
//		userRating.setUserRating(Arrays.asList(
//				new RatingDto("0", 0)
//				));
//		 return userRating;
//	}
//	

	@HystrixCommand(fallbackMethod = "getFallBackUserRating", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMillisecinds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThreholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") })
	public UserRatingDto getUserRating(String userId) {
		UserRatingDto userRatingDto = null;
		userRatingDto = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users2/" + userId,
				UserRatingDto.class);
		System.out.println("userRating Update with command Propertis res: " + userRatingDto);
		return userRatingDto;
	}

}
