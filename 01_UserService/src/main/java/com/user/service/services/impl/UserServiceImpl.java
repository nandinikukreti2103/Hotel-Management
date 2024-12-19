package com.user.service.services.impl;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.client.HotelService;
import com.user.service.client.RatingService;
import com.user.service.repository.UserRepository;
import com.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
  //  private final RestTemplate restTemplate;
    private final HotelService hotelService;
    private final RatingService ratingService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

//    @Override  : using restTemplate
//    public User getUserById(String userId) {
//        //get user from db with the help of user repo
//        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with this id: " + userId));
//
//        //fetch ratings by the above user
//        //localhost:8083/rating/get-rating-by-userId/397fb56d-c50d-48d7-b248-9d89dd3acb6c
//
//       // String url = "http://localhost:8083/rating/get-rating-by-userId/"+user.getUserId();
//        String url = "http://RATINGSERVICE/rating/get-rating-by-userId/"+user.getUserId();
//        Rating[]  ratingsByUser = restTemplate.getForObject(url, Rating[].class);
//        logger.info("{}",ratingsByUser);
//
//        List<Rating> ratings = Arrays.stream(ratingsByUser).toList();
//
//        List<Rating> hotelRatingList = ratings.stream().map(rating -> {
//
//            //api call to hotel service
//            //http://localhost:8082/hotel/a4e6d280-67a0-47b2-a729-8cbe4e4cb7d9
//
//            //ResponseEntity<Hotel> forHotelEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotel/"+rating.getHotelId(), Hotel.class);
//           // Hotel hotel = forHotelEntity.getBody();
//            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//
//            //set the hotel to rating
//            rating.setHotel(hotel);
//
//            //return the rating
//            return rating;
//        }).collect(Collectors.toList());
//
//        user.setRating(hotelRatingList);
//
//        return user;
//    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id: " + userId));

        List<Rating> ratings = ratingService.getAllRatingsByUserId(userId);
        log.info("Ratings fetched: {}", ratings);

        List<Rating> hotelRatingList = ratings.stream().peek(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
        }).collect(Collectors.toList());

        user.setRating(hotelRatingList);

        return user;
    }

    @Override
    public User updateUser(String userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user does not exist with this id"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setGender(user.getGender());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
