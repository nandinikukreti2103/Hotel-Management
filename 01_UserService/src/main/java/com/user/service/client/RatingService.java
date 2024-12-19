package com.user.service.client;

import com.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @GetMapping("/ratings/get-rating-by-userId/{userId}")
    List<Rating> getAllRatingsByUserId(@PathVariable("userId") String userId);

}
