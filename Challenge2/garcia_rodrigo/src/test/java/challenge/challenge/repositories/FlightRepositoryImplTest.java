package challenge.challenge.repositories;

import challenge.challenge.dto.FlightDTO;
import challenge.challenge.dto.FlightTestHelper;
import challenge.challenge.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Flight Repository Test")
class FlightRepositoryImplTest {

    FlightRepository flightRepository;

    FlightTestHelper flightTestHelper;

    @BeforeEach
    void setUp(){
        flightRepository = new FlightRepositoryImpl("src/main/resources/dbFlightsTest.json");
        flightTestHelper = new FlightTestHelper();
    }

    @DisplayName("Get Flights")
    @Test
    void getFlights_shouldReturnOK(){
        List<FlightDTO> resultList = flightRepository.loadFlightDB();
        List<FlightDTO> expectedList = flightTestHelper.getTestFlights();
        assertThat(resultList).isEqualTo(expectedList);
    }

}