package challenge.challenge.utils;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.*;
import challenge.challenge.repositories.FlightRepository;
import challenge.challenge.repositories.HotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationUtilsImpl implements  ValidationUtils{

    private HotelRepository hotelRepository;
    private FlightRepository flightRepository;
    public ValidationUtilsImpl(HotelRepository hotelRepository,FlightRepository flightRepository)
    {this.hotelRepository = hotelRepository;
    this.flightRepository = flightRepository;}

    //Validates param for filtered search
    @Override
    public void validateFilters(FilterDTO filterDTO) throws ApiException {

        if (filterDTO.getDateFrom() != null && filterDTO.getDateTo() != null) {
            if (filterDTO.getDateTo().compareTo(filterDTO.getDateFrom()) <= 0) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "DateFrom bigger than DateTo");
            }
        }

        if(filterDTO.getDestination() != null){
            if (!filterDTO.getDestination().equals("")) {
                boolean cityNameMatch = false;
                for (int i = 0; i < hotelRepository.loadHotelsDB().size(); i++) {
                    if (hotelRepository.loadHotelsDB().get(i).getDestination().equals(filterDTO.getDestination())) {
                        cityNameMatch = true;
                        break;
                    }
                }
                if (!cityNameMatch) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "No city Named:" + filterDTO.getDestination());
                }
            } else throw new ApiException(HttpStatus.BAD_REQUEST, "Empty City");
        }
    }

    //validates all information needed in booking hotel method
    @Override
    public void validateBookingRequest(HotelRequestDTO hotelRequestDTO) throws ApiException {

        validateRequestDate(hotelRequestDTO.getBooking().getDateFrom(), hotelRequestDTO.getBooking().getDateTo());
        validateBooking(hotelRequestDTO);
        validatePayment(hotelRequestDTO.getBooking().getPaymentMethod().getType(),
                hotelRequestDTO.getBooking().getPaymentMethod().getDues());
        validateRoomTypeAndPeople(hotelRequestDTO.getBooking().getRoomType(),hotelRequestDTO.getBooking().getPeopleAmount());
        validateEmailFormat(hotelRequestDTO.getUserName());
    }

    //validate all information needed in booking flights method
    @Override
    public void validateFlightRequest(FlightRequestDTO flightRequestDTO) throws ApiException {
        validateRequestDate(flightRequestDTO.getFlightReservation().getDateFrom(),
                flightRequestDTO.getFlightReservation().getDateTo());
        validatePayment(flightRequestDTO.getFlightReservation().getPaymentMethod().getType(),
                flightRequestDTO.getFlightReservation().getPaymentMethod().getDues());
        validateEmailFormat(flightRequestDTO.getUserName());
        validateFlightReservation(flightRequestDTO);
    }

    //validate dateFrom is previous to dateTo
    @Override
    public void validateRequestDate(Date dateFrom, Date dateTo) throws ApiException{
        if (dateFrom != null ) {
            if (dateTo != null) {
                if (dateTo.compareTo(dateFrom) < 0) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "DateFrom bigger than DateTo");
                }
            }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missin DateTo");
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing DateFrom");
    }

    //validate all the payment information
    @Override
    public void validatePayment(String paymentMethod,Integer dues) throws ApiException {
        if(paymentMethod != null){
            if (dues != null) {
                if (paymentMethod.equals("CREDIT") && (dues < 1 || dues > 12)) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "Dues for CREDIT must be between 1 and 12");
                } else if (paymentMethod.equals("DEBIT") && dues != 1) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "Dues for DEBIT must be 1");
                }else if (!paymentMethod.equals("CREDIT") && !paymentMethod.equals("DEBIT")){
                    throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid Payment Method");}
            }else throw new ApiException(HttpStatus.BAD_REQUEST, "Missing Dues Parameter");
        }else throw new ApiException(HttpStatus.BAD_REQUEST, "Missing Payment Method");
    }

    //validate people number matching with roomType. People can book a room bigger than their require
    @Override
    public void validateRoomTypeAndPeople(String roomType,Integer peopleAmount) throws ApiException{
        if(peopleAmount != null){
            if(peopleAmount > 0 && peopleAmount <= 10 ){
                if(roomType != null){
                    if (!roomType.equals("")){
                        if(peopleAmount > 1 && roomType.equals("Single")){
                            throw new ApiException(HttpStatus.BAD_REQUEST,"Single rooms are for 1 person");
                        }
                        else if(peopleAmount > 2  && roomType.equals("Doble")){
                            throw new ApiException(HttpStatus.BAD_REQUEST,"Doble rooms are for up to 2 people");
                        }
                        else if(peopleAmount > 3 && roomType.equals("Tiple")) {
                            throw new ApiException(HttpStatus.BAD_REQUEST,"Tiple rooms are for up to 3 people");
                        }
                        else if(peopleAmount >= 10 && roomType.equals("Multiple")) {
                            throw new ApiException(HttpStatus.BAD_REQUEST,"Multiple rooms are for up to 4 people");
                        }
                    }else throw new ApiException(HttpStatus.BAD_REQUEST,"Empty roomType");
                }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing roomType Parameter");
            }else throw new ApiException(HttpStatus.BAD_REQUEST,"People amount must be between 1 and 10");
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missin PeopleAmount");
    }

    //validates all the data match with the room in the booking request
    @Override
    public void validateBooking(HotelRequestDTO hotelRequestDTO) throws ApiException{
        HotelDTO hotel = new HotelDTO();
        if(hotelRequestDTO.getBooking() != null){
            if(hotelRequestDTO.getBooking().getHotelCode() != null){
                if(!hotelRequestDTO.getBooking().getHotelCode().equals("")){
                    boolean hotelCodeMatch = false;
                    //find the matching room for the request
                    for (int i = 0; i < hotelRepository.loadHotelsDB().size(); i++) {
                        if (hotelRequestDTO.getBooking().getHotelCode()
                                .equals(hotelRepository.loadHotelsDB().get(i).getCode())) {
                            hotelCodeMatch = true;
                            hotel = hotelRepository.loadHotelsDB().get(i);
                        }
                    }
                    //validates all data
                    if(!hotelCodeMatch) throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid HotelCode");
                    validateDestination(hotelRequestDTO.getBooking().getDestination(),hotel.getDestination());
                    validateHotelRoom(hotel,hotelRequestDTO.getBooking().getRoomType());
                    validateDateFrom(hotelRequestDTO.getBooking().getDateFrom(),hotel);
                    validateDateTo(hotelRequestDTO.getBooking().getDateTo(),hotel);
                    if(hotel.getBooked()){throw new ApiException(HttpStatus.BAD_REQUEST,"Room Already Booked");}
                }else throw new ApiException(HttpStatus.BAD_REQUEST,"Empty Hotel Code");
            }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Hotel Code");
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Booking");
    }

    //validate all the data match witch the flight in the reservation request
    @Override
    public void validateFlightReservation(FlightRequestDTO flightRequestDTO) throws ApiException {
        FlightDTO flight = new FlightDTO();
        if(flightRequestDTO.getFlightReservation() != null) {
            if (flightRequestDTO.getFlightReservation().getFlightNumber() != null) {
                if (!flightRequestDTO.getFlightReservation().getFlightNumber().equals("")) {
                    boolean flightMatch = false;
                    //find the matching flight for the request
                    for (int i = 0; i < flightRepository.loadFlightDB().size(); i++) {
                        if (flightRequestDTO.getFlightReservation().getFlightNumber()
                                .equals(flightRepository.loadFlightDB().get(i).getFlightNumber())) {
                            flightMatch = true;
                            flight = flightRepository.loadFlightDB().get(i);
                        }
                    }
                    //validate all data
                    if (!flightMatch) throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid Flight Number");
                    validateFlightDate(flightRequestDTO.getFlightReservation().getDateFrom(), flight.getDateFrom());
                    validateFlightDate(flightRequestDTO.getFlightReservation().getDateTo(), flight.getDateTo());
                    validateDestination(flightRequestDTO.getFlightReservation().getDestination(), flight.getDestination());
                    validateOrigin(flightRequestDTO.getFlightReservation().getOrigin(), flight.getOrigin());
                    validateSeatType(flightRequestDTO.getFlightReservation().getSeatType(),flight.getSeatType());
                }else throw new ApiException(HttpStatus.BAD_REQUEST,"Empty Flight Number");
            }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Flight Number");
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Flight Reservation");
    }

    //validate user Mail format
    @Override
    public void validateEmailFormat(String email) throws ApiException {
        String regex = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid email " + email);
    }

    //validate request room type
    @Override
    public void validateHotelRoom(HotelDTO hotel,String roomType) throws ApiException {
        if (roomType != null) {
            if (!hotel.getRoomType().equals(roomType)) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid RoomType" + roomType);
            }
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Room Type");
    }

    //validate request destination
    @Override
    public void validateDestination(String destination, String storedDestination) throws ApiException {
        if(destination != null) {
            if (!storedDestination.equals(destination)) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid Destination " + destination);
            }
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing destination");
    }

    //validate resquest origin
    @Override
    public void validateOrigin(String origin, String storedOrigin) throws ApiException {
        if(origin != null) {
            if (!storedOrigin.equals(origin)) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid Origin " + origin);
            }
        }else throw new ApiException(HttpStatus.BAD_REQUEST,"Missing Origin");
    }

    //validate DateFrom in room booking
    @Override
    public void validateDateFrom(Date date, HotelDTO hotel) throws ApiException {
        if(date != null){
            if(hotel.getDateFrom().compareTo(date) >= 0){
                throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid DateFrom " + date);
            }
        }
    }

    //validate dateTO in room booking
    @Override
    public void validateDateTo(Date date, HotelDTO hotel) throws ApiException {
        if(date != null){
            if(hotel.getDateTo().compareTo(date) <= 0){
                throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid DateTo " + date);
            }
        }
    }

    //validate dates in flight reservation
    @Override
    public void validateFlightDate(Date date, Date storedDate) throws ApiException {
        if(date != null){
            if(storedDate.compareTo(date) == 0){
                throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid DateTo " + date);
            }
        }
    }

    public void validateSeatType(String seatType, String storedType)throws ApiException{
        if(seatType != null){
            if(storedType.equals(seatType)){
                throw new ApiException(HttpStatus.BAD_REQUEST,"Invalid SeatType" + seatType);
            }
        }
    }
}
