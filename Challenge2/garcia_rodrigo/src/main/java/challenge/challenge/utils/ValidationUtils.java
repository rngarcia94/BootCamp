package challenge.challenge.utils;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.*;

import java.util.Date;

public interface ValidationUtils {

    void validateFilters(FilterDTO filterDTO) throws ApiException;

    void validateRoomTypeAndPeople(String roomType,Integer peopleAmount) throws ApiException;

    void validateEmailFormat(String email) throws ApiException;

    void validateRequestDate(Date dateFrom, Date dateTo) throws ApiException;

    void validatePayment(String paymentMethod,Integer dues) throws ApiException;

    void validateHotelRoom(HotelDTO hotel, String roomType) throws ApiException;

    void validateDestination(String destination,String storedDestination) throws ApiException;

    void validateOrigin(String origin, String storedOrigin) throws ApiException;

    void validateDateFrom(Date date,HotelDTO hotel) throws ApiException;

    void validateDateTo(Date date,HotelDTO hotel) throws ApiException;

    void validateBooking(HotelRequestDTO hotelRequestDTO) throws ApiException;

    void validateBookingRequest(HotelRequestDTO hotelRequestDTO) throws ApiException;

    void validateFlightRequest(FlightRequestDTO flightRequestDTO)throws ApiException;

    void validateFlightReservation(FlightRequestDTO flightRequestDTO) throws ApiException;

    void validateFlightDate(Date date, Date storedDate) throws ApiException;

    void validateSeatType(String seatType, String storedType)throws ApiException;
}
