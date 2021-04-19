package challenge.challenge.repositories;

import challenge.challenge.dto.FlightDTO;
import challenge.challenge.dto.HotelDTO;

import java.util.ArrayList;

public interface FlightRepository {

    ArrayList<FlightDTO> loadFlightDB ();
}
