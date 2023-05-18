package com.UserService.service.impl;

import com.UserService.domain.Hotel;
import com.UserService.domain.Rating;
import com.UserService.domain.User;
import com.UserService.dto.UserDto;
import com.UserService.exception.ResourceNotFoundException;
import com.UserService.external.service.HotelService;
import com.UserService.repository.UserRepository;
import com.UserService.service.UserService;
import com.UserService.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
    }

    private List<Rating> getUserRatings(UUID userId){
        Rating[] userRatingList = restTemplate.getForObject("http://RATING-SERVICE/api/v1/ratings/user/"+userId, Rating[].class);

        List<Rating> ratings = Arrays.stream(userRatingList).collect(Collectors.toList());

        List<Rating> userRatingWithHotelDetails = ratings.stream().map(rating -> {
            //api call to hotelservice
            //localhost:8082/api/v1/hotels/b8f92315-5b8b-4948-ba43-9547218ec70f

            //using restTemplate
//            ResponseEntity<Hotel> hotelDetail = restTemplate.getForEntity("http://HOTEL-SERVICE/api/v1/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = hotelDetail.getBody();

            //alternative of restTemplate -feign client
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //set the hotel to rating entity
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        return userRatingWithHotelDetails;

    }

    @Override
    public UserDto store(User user) {
        UUID userId = UUID.randomUUID();
        user.setId(userId);
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return userMapper.modelTODto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();

        List<User> userListWithRating = userList.stream().map(user -> {
            List<Rating> userRatingList = getUserRatings(user.getId());
            user.setRatings(userRatingList);
            return user;
        }).collect(Collectors.toList());

        return userMapper.modelTODtos(userListWithRating);
    }

    @Override
    public UserDto get(UUID userId) {

        //get user from db with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User is not found with id: "+ userId));
        //fetch rating of the above user from RATING_SERVICE
        //localhost:8083/api/v1/ratings/user/41d89001-3739-427c-9438-77ae51268ce3

       List<Rating> userRatingWithHotelDetails = getUserRatings(user.getId());

        user.setRatings(userRatingWithHotelDetails);

        return userMapper.modelTODto(user);
    }
}