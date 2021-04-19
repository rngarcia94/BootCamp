package challenge.challenge.services;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.*;
import challenge.challenge.repositories.FlightRepository;
import challenge.challenge.repositories.FlightRepositoryImpl;
import challenge.challenge.repositories.HotelRepository;
import challenge.challenge.repositories.HotelRepositoryImpl;
import challenge.challenge.utils.FileUtils;
import challenge.challenge.utils.FileUtilsImpl;
import challenge.challenge.utils.ValidationUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceImplTest {

    private FlightService flightService;
    private ValidationUtilsImpl validationUtils;
    private HotelRepository hotelRepository;
    private FlightRepository flightRepository;
    private FileUtils fileUtils;

    @BeforeEach
    public void init() {

        flightRepository= new FlightRepositoryImpl("src/main/resources/dbFlightsTest.json");
        hotelRepository = new HotelRepositoryImpl("src/main/resources/dbHotelsTest.json");
        validationUtils = new ValidationUtilsImpl(hotelRepository,flightRepository);
        flightService = new FlightServiceImpl(flightRepository,validationUtils);
    }

    @DisplayName("Check get all Flights ")
    @Test
    public void validateGetAllFlights_shouldReturnOK() throws ApiException {
        FilterDTO filterDTO = new FilterDTO();
        //validate get all Flights
        assertDoesNotThrow(() -> flightService.getFlights(filterDTO));
    }

    @DisplayName("Check get filtered Flights ")
    @Test
    public void validateGetFilteredFlights_shouldReturnOK() throws ApiException {
        //Date start counting in 1900, to get 2021, we put 121.
        //Months start in 0, January = 0
        Date dateFrom = new Date(121, 1,13);
        Date dateTo = new Date(121,1,28);
        FilterDTO filterDTO = new FilterDTO("Buenos Aires",dateFrom,dateTo);
        //validate get some Flights
        assertDoesNotThrow(() -> flightService.getFlights(filterDTO));
    }

    @DisplayName("Check reserve Flight Method")
    @Test
    public void validateBookHotelMethod_shouldReturnOK()throws ApiException{
        fileUtils = new FileUtilsImpl("src/main/resources/FlightResquestTest.json");
        FlightRequestDTO flightRequestDTO = fileUtils.loadFlightRequest();
        //Validate reserve flight Method
        assertDoesNotThrow(() -> flightService.reserveFlight(flightRequestDTO));
    }

    @DisplayName("Check ERROR reserving Flight Method")
    @Test
    public void checkValidBooking_shouldReturnERROR()throws ApiException{
        fileUtils = new FileUtilsImpl("src/main/resources/FlightRequestTestERROR.json");
        FlightRequestDTO flightRequestDTO = fileUtils.loadFlightRequest();
        //Validate reserve flight Method Error
        assertThrows(ApiException.class,() -> flightService.reserveFlight(flightRequestDTO));
    }


}