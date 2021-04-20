package challenge.challenge.controllers;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.*;
import challenge.challenge.services.FlightService;
import challenge.challenge.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private FlightService flightService;

    public ReservationController(HotelService hotelService,FlightService flightService){
        this.hotelService = hotelService;
        this.flightService = flightService;
    }

    //get,filter and show the hotels
    @GetMapping("/hotels/")
    public ResponseEntity getHotels(FilterDTO hotel) throws ApiException {
        return new ResponseEntity(hotelService.getHotels(hotel), HttpStatus.OK);
    }

    //needs a json with a booking request and return the booking response
    @PostMapping("/booking")
    public ResponseEntity bookHotel(@RequestBody HotelRequestDTO requestDTO) throws ApiException {
        return new ResponseEntity(hotelService.bookHotel(requestDTO),HttpStatus.OK);
    }

    //get,filter and show the flights
    @GetMapping("/flights")
    public ResponseEntity getFlights(FilterDTO flight) throws ApiException {
        return new ResponseEntity(flightService.getFlights(flight),HttpStatus.OK);
    }

    //needs a json with a reservation request and return the Reservation response
    @PostMapping("/flight-reservation")
    public ResponseEntity bookFlight(@RequestBody FlightRequestDTO requestDTO) throws ApiException {
        return new ResponseEntity(flightService.reserveFlight(requestDTO),HttpStatus.OK);
    }

}
