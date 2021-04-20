package challenge.challenge.services;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.*;

import java.util.List;

public interface FlightService {

     List<FlightDTO> getFlights(FilterDTO FilterDTO) throws ApiException;

      FlightResponseDTO reserveFlight(FlightRequestDTO flightRequestDTO) throws ApiException;
}
