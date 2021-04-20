package challenge.challenge.repositories;

import challenge.challenge.dto.FlightDTO;
import challenge.challenge.dto.HotelDTO;
import challenge.challenge.dto.HotelTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Hotel Repository")
class HotelRepositoryImplTest {

    private HotelRepository hotelRepository;
    private HotelTestHelper hotelTestHelper;

    @BeforeEach
    void setUp() {
        hotelRepository = new HotelRepositoryImpl("src/main/resources/dbHotelsTest.json");
        hotelTestHelper = new HotelTestHelper();
    }

    @DisplayName("Get Hotels")
    @Test
    void getFlights_shouldReturnOK(){
        List<HotelDTO> resultList = hotelRepository.loadHotelsDB();
        List<HotelDTO> expectedList = hotelTestHelper.getAllTestHotels();
        assertThat(resultList).isEqualTo(expectedList);
    }
}