package com.hotel.service.services;

import com.hotel.service.entity.Hotel;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;


    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setHotelId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("hotel not found with the id: " + hotelId));
    }

    @Override
    public Hotel updateHotel(String hotelId, Hotel hotelDetails) {
        Hotel existingHotel = hotelRepository.findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("hotel does not exist with the id"));

        existingHotel.setName(hotelDetails.getName());
        existingHotel.setLocation(hotelDetails.getLocation());
        existingHotel.setAbout(hotelDetails.getAbout());

        return hotelRepository.save(existingHotel);
    }

    @Override
    public void deleteHotel(String hotelId) {

        hotelRepository.deleteById(hotelId);
    }
}
