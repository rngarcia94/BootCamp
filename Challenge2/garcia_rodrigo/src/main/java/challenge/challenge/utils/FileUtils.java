package challenge.challenge.utils;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.FlightDTO;
import challenge.challenge.dto.FlightRequestDTO;
import challenge.challenge.dto.HotelDTO;
import challenge.challenge.dto.HotelRequestDTO;

import java.util.ArrayList;
import java.util.List;

public interface FileUtils {

    HotelRequestDTO loadHotelRequest();
    public ArrayList<HotelDTO> loadHotelsDB();

    public ArrayList<FlightDTO> loadFlightDB();

    FlightRequestDTO loadFlightRequest();

    void writeFile(String filePath, List<HotelDTO> hotelDTOList) throws ApiException;
}
