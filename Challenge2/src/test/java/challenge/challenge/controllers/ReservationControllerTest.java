package challenge.challenge.controllers;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.FilterDTO;
import challenge.challenge.dto.FlightRequestDTO;
import challenge.challenge.dto.HotelRequestDTO;
import challenge.challenge.services.FlightService;
import challenge.challenge.services.HotelService;
import challenge.challenge.utils.FileUtils;
import challenge.challenge.utils.FileUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Controller Test")
class ReservationControllerTest {

    private FileUtils fileUtils;
    @Mock
    private HotelService hotelService;

    @Mock
    private FlightService flightService;

    ReservationController reservationController;

    @BeforeEach
    void setUp(){
        reservationController = new ReservationController(hotelService,flightService);
    }

    @DisplayName("Get All Hotels")
    @Test
    void getAllHotels_shouldReturnOK()throws ApiException{
        FilterDTO filter = new FilterDTO();

        assertDoesNotThrow(()->reservationController.getHotels(filter));
    }

    @DisplayName("Get Filtered Hotels")
    @Test
    void getFilteredHotels_shouldReturnOK()throws ApiException{
        Date dateFrom = new Date(121,02,10);
        Date dateTo = new Date(121,02,15);
        FilterDTO filterDTO = new FilterDTO("Buenos Aires",dateFrom,dateTo);

        assertDoesNotThrow(()->reservationController.getHotels(filterDTO));
    }

    @DisplayName("Book Hotel")
    @Test
    void bookHotel_shouldReturnOK()throws ApiException{
        fileUtils = new FileUtilsImpl("src/main/resources/HotelResquestTest.json");
        HotelRequestDTO hotelRequestDTO = fileUtils.loadHotelRequest();
        //Validate Booking Hotel Method
        assertDoesNotThrow(() -> reservationController.bookHotel(hotelRequestDTO));
    }

    @DisplayName("Check get all Flights ")
    @Test
    public void validateGetAllFlights_shouldReturnOK() throws ApiException {
        FilterDTO filterDTO = new FilterDTO();
        //validate get all Flights
        assertDoesNotThrow(() -> reservationController.getFlights(filterDTO));
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
        assertDoesNotThrow(() -> reservationController.getFlights(filterDTO));
    }

    @DisplayName("Check reserve Flight Method")
    @Test
    public void validateBookHotelMethod_shouldReturnOK()throws ApiException{
        fileUtils = new FileUtilsImpl("src/main/resources/FlightResquestTest.json");
        FlightRequestDTO flightRequestDTO = fileUtils.loadFlightRequest();
        //Validate reserve flight Method
        assertDoesNotThrow(() -> reservationController.bookFlight(flightRequestDTO));
    }

}