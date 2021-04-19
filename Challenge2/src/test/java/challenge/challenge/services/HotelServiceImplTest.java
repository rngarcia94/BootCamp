package challenge.challenge.services;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.HotelDTO;
import challenge.challenge.dto.FilterDTO;
import challenge.challenge.dto.HotelRequestDTO;
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

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelServiceImplTest {

    private HotelService hotelService;
    private ValidationUtilsImpl validationUtils;
    private HotelRepository hotelRepository;
    private FlightRepository flightRepository;
    private FileUtils fileUtils;

    @BeforeEach
    public void init() {

        flightRepository= new FlightRepositoryImpl("src/main/resources/dbFlightsTest.json");
        hotelRepository = new HotelRepositoryImpl("src/main/resources/dbHotelsTest.json");
        validationUtils = new ValidationUtilsImpl(hotelRepository,flightRepository);
        hotelService = new HotelServiceImpl(hotelRepository,validationUtils);
    }

    @DisplayName("Check get all hotels ")
    @Test
    public void validateGetAllHotels_shouldReturnOK() throws ApiException {
        FilterDTO filterDTO = new FilterDTO();
        //validate get show all hotels

        assertDoesNotThrow(() -> hotelService.getHotels(filterDTO));
    }

    @DisplayName("Check get filtered hotels ")
    @Test
    public void validateGetFilteredHotels_shouldReturnOK() throws ApiException {
        //Date start counting in 1900, to get 2021, we put 121.
        Date dateFrom = new Date(121,02,10);
        Date dateTo = new Date(121,02,15);
        FilterDTO filterDTO = new FilterDTO("Buenos Aires",dateFrom,dateTo);
        //validate get show all hotels
        assertDoesNotThrow(() -> hotelService.getHotels(filterDTO));
    }


    /* BE AWARE!!!
    This method affects the JSON database
    after running the hotel passed in the payload will change its booked attribute
    from false to true.
    after than if your try to re run the test it will fail.
     */

    @DisplayName("Check book Hotel Method")
    @Test
    public void validateBookHotelMethod_shouldReturnOK()throws ApiException{
        fileUtils = new FileUtilsImpl("src/main/resources/HotelResquestTest.json");
        HotelRequestDTO hotelRequestDTO = fileUtils.loadHotelRequest();
        //Validate Booking Hotel Method
        assertDoesNotThrow(() -> hotelService.bookHotel(hotelRequestDTO));
    }

    @DisplayName("Check ERROR Booking Hotel Method")
    @Test
    public void checkValidBooking_shouldReturnERROR()throws ApiException{
        fileUtils = new FileUtilsImpl("src/main/resources/HotelResquestTestERROR.json");
        HotelRequestDTO hotelRequestDTO = fileUtils.loadHotelRequest();
        //Validate Booking Hotel Method Error
        assertThrows(ApiException.class,() -> hotelService.bookHotel(hotelRequestDTO));
    }

}