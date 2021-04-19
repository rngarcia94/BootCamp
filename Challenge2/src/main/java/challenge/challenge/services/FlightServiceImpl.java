package challenge.challenge.services;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.*;
import challenge.challenge.repositories.FlightRepository;
import challenge.challenge.utils.ValidationUtilsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService{

    private FlightRepository flightRepository;
    private ValidationUtilsImpl validationUtils;

    public FlightServiceImpl(FlightRepository flightRepository, ValidationUtilsImpl validationUtils) {
        this.flightRepository = flightRepository;
        this.validationUtils = validationUtils;}

    //gets and filter the flights
    @Override
    public List<FlightDTO> getFlights(FilterDTO FilterDTO) throws ApiException {
        if(FilterDTO != null){
            validationUtils.validateFilters(FilterDTO);
        }
        List<FlightDTO> flightDTOList = new ArrayList<FlightDTO>();
        flightDTOList = flightRepository.loadFlightDB();
        flightDTOList = flightDTOList.stream()
                .filter(p -> {
                    boolean matches = true;
                    if (FilterDTO.getDestination() != null ) {
                        matches = matches && p.getDestination().equals(FilterDTO.getDestination());
                    }
                    if (FilterDTO.getDateFrom() != null) {
                        matches = matches && FilterDTO.getDateFrom().compareTo(p.getDateFrom()) <= 0;
                    }
                    if (FilterDTO.getDateTo() != null) {
                        matches = matches && FilterDTO.getDateTo().compareTo(p.getDateTo()) >= 0;
                    }
                    return matches;
                }).collect(Collectors.toList());
        if (flightDTOList.size() == 0){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Flights Not Found ");
        }
        else return flightDTOList;
    }

    //Reserve method: calls the validation method and the interest calculator
    //form the Reservation response
    @Override
    public FlightResponseDTO reserveFlight(FlightRequestDTO flightRequestDTO) throws ApiException {

    List<FlightDTO> flightDTOList = flightRepository.loadFlightDB();
    validationUtils.validateFlightRequest(flightRequestDTO);

        double pricePerSeat = 0;
        for (FlightDTO flightDTO : flightDTOList) {
            if (flightRequestDTO.getFlightReservation().getFlightNumber().equals(flightDTO.getFlightNumber())
                && flightRequestDTO.getFlightReservation().getSeatType().equals(flightDTO.getSeatType())){

                pricePerSeat = flightDTO.getPrice();
            }
        }

        double interest = calculateInterest(flightRequestDTO.getFlightReservation().getPaymentMethod().getDues()
                ,flightRequestDTO.getFlightReservation().getPaymentMethod().getType());
        double amount = pricePerSeat * flightRequestDTO.getFlightReservation().getSeats();
        double total = amount + (amount * interest);

        FlightReservationResponseDTO flightReservationResponseDTO =
                new FlightReservationResponseDTO(flightRequestDTO.getFlightReservation().getDateFrom(),
                        flightRequestDTO.getFlightReservation().getDateTo(),flightRequestDTO.getFlightReservation().getOrigin(),
                        flightRequestDTO.getFlightReservation().getDestination(),
                        flightRequestDTO.getFlightReservation().getFlightNumber(),flightRequestDTO.getFlightReservation().getSeats(),
                        flightRequestDTO.getFlightReservation().getSeatType(),flightRequestDTO.getFlightReservation().getPeople());
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO(200,"The Process Ended Correctly");

        return new FlightResponseDTO(flightRequestDTO.getUserName(),
                amount,interest*100,total, flightReservationResponseDTO,responseStatusDTO);
    }

    //calcuales payment interest
    private Double calculateInterest(Integer dues, String paymentMethod){
        double interest = 0.0;
        if(paymentMethod.equals("CREDIT")){
            if(dues <= 3){
                interest = 0.05 ;
            }
            else if(dues <= 6){
                interest = 0.1 ;
            }
            else if(dues <= 9){
                interest = 0.15 ;
            }
            else if(dues <= 12){
                interest = 0.2 ;
            }
        }
        return interest;
    }

}
