package com.hotel.service.services;

import com.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {


    Hotel saveHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotelById(String hotelId);

    Hotel updateHotel(String hotelId, Hotel hotelDetails);

    void deleteHotel(String hotelId);
}
