package challenge.challenge.repositories;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.HotelDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface HotelRepository {

    ArrayList<HotelDTO> loadHotelsDB ();

    void bookHotel(HotelDTO hotelToBook) throws ApiException;
}
