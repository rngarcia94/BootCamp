package challenge.challenge.utils;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.FilterDTO;
import challenge.challenge.dto.FlightRequestDTO;
import challenge.challenge.dto.HotelRequestDTO;
import challenge.challenge.dto.PaymentDTO;
import challenge.challenge.repositories.FlightRepository;
import challenge.challenge.repositories.FlightRepositoryImpl;
import challenge.challenge.repositories.HotelRepository;
import challenge.challenge.repositories.HotelRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    private ValidationUtils validationUtils;
    private HotelRepository hotelRepository;
    private FlightRepository flightRepository;
    private FileUtils fileUtils;

    @BeforeEach
    public void init() {

        flightRepository= new FlightRepositoryImpl("src/main/resources/dbFlightsTest.json");
        hotelRepository = new HotelRepositoryImpl("src/main/resources/dbHotelsTest.json");
        validationUtils = new ValidationUtilsImpl(hotelRepository,flightRepository);

    }

    @DisplayName("Check GetHotels Filter ")
    @Test
    public void validateGetHotelFilter_shouldReturnOK(){
        Date dateFrom = new Date(2021,02,10);
        Date dateTo = new Date(2021,02,15);
        FilterDTO filterDTO = new FilterDTO("Buenos Aires",dateFrom,dateTo);
        //Validate Dates are OK and destination exist
        assertDoesNotThrow(()-> validationUtils.validateFilters(filterDTO));

    }

    @DisplayName("Check GetHotels Filter dateTo bigger than dateFrom ERROR ")
    @Test
    public void validateGetHotelFilter_shouldReturnERROR(){
        Date dateFrom = new Date(2021,02,10);
        Date dateTo = new Date(2021,02,8);
        FilterDTO filterDTO = new FilterDTO("Buenos Aires",dateFrom,dateTo);
        //Validate DateFrom afterward DateTo
        assertThrows(ApiException.class,() -> validationUtils.validateFilters(filterDTO));
    }

    @DisplayName("Check GetHotels Filter Destination Does not Exist")
    @Test
    public void validateGetHotelFilter_shouldReturnDestinationERROR(){
        Date dateFrom = new Date(2021,02,10);
        Date dateTo = new Date(2021,02,15);
        FilterDTO filterDTO = new FilterDTO("Mendoza",dateFrom,dateTo);
        //validate Destination DOES NOT exist
        assertThrows(ApiException.class,() -> validationUtils.validateFilters(filterDTO));
    }

    @DisplayName("Check dateFrom is previous to dateTo")
    @Test
    public void validateIntervalDate_shouldReturnOk() {
        Date dateFrom = new Date(2021,02,10);
        Date dateTo = new Date(2021,02,15);
        // Validate date pass
        assertDoesNotThrow(() -> validationUtils.validateRequestDate(dateFrom, dateTo));
    }

    @DisplayName("Return ApiException if the dateFrom isn't previous to dateTo")
    @Test
    public void validateInvalidIntervalDate_shouldReturnApiException() {
        Date dateFrom = new Date(2021,02,15);
        Date dateTo = new Date(2021,02,10);
        // Validate date DONT pass
        assertThrows(ApiException.class,() -> validationUtils.validateRequestDate(dateFrom, dateTo));
    }

    @DisplayName("Check valid room type")
    @Test
    public void checkValidRoomType_shouldReturnOK() {
        // Validate doble room
        assertDoesNotThrow(() -> validationUtils.validateRoomTypeAndPeople("Doble", 2));
    }

    @DisplayName("Check valid multiple room type")
    @Test
    public void checkValidMultipleRoomType_shouldReturnOK() {
        // Validate multiple room
        assertDoesNotThrow(() -> validationUtils.validateRoomTypeAndPeople("Multiple", 6));
    }

    @DisplayName("Check valid Tiple room type")
    @Test
    public void checkValidTipleRoomType_shouldReturnOK() {
        // Validate triple room
        assertDoesNotThrow(() -> validationUtils.validateRoomTypeAndPeople("Tiple", 1));
    }

    @DisplayName("Check valid Single room type")
    @Test
    public void checkValidSingleRoomType_shouldReturnOK() {
        // Validate single room
        assertDoesNotThrow(() -> validationUtils.validateRoomTypeAndPeople("Single", 1));
    }

    @DisplayName("Check invalid room type ")
    @Test
    public void checkValidRoomType_shouldReturnERROR() {
        // Validate doble room error
        assertThrows(ApiException.class,() -> validationUtils.validateRoomTypeAndPeople("Doble", 4));
    }

    @DisplayName("Check Credit Payment Method")
    @Test
    public void checkValidatePaymentMethod_shouldReturnOK() throws ApiException {
        PaymentDTO paymentDTO = new PaymentDTO("CREDIT","1234-1234-1234-1234",3);
        //Validate CREDIT PAYMENT
        assertDoesNotThrow(() -> validationUtils.validatePayment(paymentDTO.getType(), paymentDTO.getDues()));
    }

    @DisplayName("Check Debit Payment Method")
    @Test
    public void checkValidateDebitPaymentMethod_shouldReturnOK() throws ApiException {
        PaymentDTO paymentDTO = new PaymentDTO("DEBIT","1234-1234-1234-1234",1);
        //Validate DEBIT PAYMENT
        assertDoesNotThrow(() -> validationUtils.validatePayment(paymentDTO.getType(), paymentDTO.getDues()));
    }

    @DisplayName("Check Credit Payment Method ERROR")
    @Test
    public void checkValidatePaymentMethod_shouldReturnERROR() throws ApiException {
        PaymentDTO paymentDTO = new PaymentDTO("CREDIT","1234-1234-1234-1234",18);
        //Validate CREDIT PAYMENT ERROR
        assertThrows(ApiException.class,() -> validationUtils.validatePayment(paymentDTO.getType(), paymentDTO.getDues()));
    }

    @DisplayName("Check Debit Payment Method ERROR")
    @Test
    public void checkValidateDebitPaymentMethod_shouldReturnERROR() throws ApiException {
        PaymentDTO paymentDTO = new PaymentDTO("DEBIT","1234-1234-1234-1234",3);
        //Validate DEBIT PAYMENT ERROR
        assertThrows(ApiException.class,() -> validationUtils.validatePayment(paymentDTO.getType(), paymentDTO.getDues()));
    }

    @DisplayName("Check User Email")
    @Test
    public void checkValidUserMail_shouldReturnOK() throws ApiException{
        String mail = "juan@gmail.com";
        //Validate mail
        assertDoesNotThrow(() -> validationUtils.validateEmailFormat(mail));
    }

    @DisplayName("Check User Email ERROR")
    @Test
    public void checkValidUserMail_shouldReturnERROR() throws ApiException{
        String mail = "juan";
        //Validate mail ERROR
        assertThrows(ApiException.class,() -> validationUtils.validateEmailFormat(mail));
    }

    @DisplayName("Check HOtel Booking Validator")
    @Test
    public void checkValidBooking_shouldReturnOK()throws ApiException{
        fileUtils = new FileUtilsImpl("src/main/resources/HotelResquestTest.json");
        HotelRequestDTO hotelRequestDTO = fileUtils.loadHotelRequest();
        //Validate all Booking Data
        assertDoesNotThrow(() -> validationUtils.validateBookingRequest(hotelRequestDTO));
    }

    @DisplayName("Check ERROR Booking Validator")
    @Test
    public void checkValidBooking_shouldReturnERROR()throws ApiException{
        fileUtils = new FileUtilsImpl("src/main/resources/HotelResquestTestERROR.json");
        HotelRequestDTO hotelRequestDTO = fileUtils.loadHotelRequest();
        //Validate Hotel Code ERROR
        assertThrows(ApiException.class,() -> validationUtils.validateBookingRequest(hotelRequestDTO));
    }

    @DisplayName("Check Flight Reservation Validator")
    @Test
    public void checkValidReservation_shouldReturnOK()throws ApiException{
        fileUtils = new FileUtilsImpl("src/main/resources/FlightResquestTest.json");
        FlightRequestDTO flightRequestDTO = fileUtils.loadFlightRequest();
        //Validate all Booking Data
        assertDoesNotThrow(() -> validationUtils.validateFlightRequest(flightRequestDTO));
    }

}
