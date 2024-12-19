package com.rating.service.controller;

import com.rating.service.entity.Rating;
import com.rating.service.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating ratingCreated = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingCreated);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Rating>> getAllRatings(){
       List<Rating> allRatings = ratingService.getAllRatings();
        return ResponseEntity.ok(allRatings);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable String ratingId){

        Rating rating = ratingService.getRatingById(ratingId);
        return ResponseEntity.ok(rating);
    }

    @DeleteMapping("/delete/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable String ratingId){
        ratingService.deleteRating(ratingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-rating-by-userId/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId){
        List<Rating> allRatings = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(allRatings);
    }

    @GetMapping("/get-rating-by-hotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId){
        List<Rating> allRatings = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(allRatings);
    }
}
