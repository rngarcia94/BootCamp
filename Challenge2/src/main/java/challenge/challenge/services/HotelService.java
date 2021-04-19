package challenge.challenge.services;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.HotelDTO;
import challenge.challenge.dto.FilterDTO;
import challenge.challenge.dto.HotelRequestDTO;
import challenge.challenge.dto.HotelReservationDTO;

import java.util.List;

public interface HotelService {

    List<HotelDTO> getHotels (FilterDTO hotelFilterDTO) throws ApiException;

    HotelReservationDTO bookHotel(HotelRequestDTO hotelRequestDTO) throws ApiException;
}
