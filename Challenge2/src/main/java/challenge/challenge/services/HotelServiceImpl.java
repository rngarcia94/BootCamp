package challenge.challenge.services;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.*;
import challenge.challenge.repositories.HotelRepository;
import challenge.challenge.utils.ValidationUtilsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private ValidationUtilsImpl validationUtils;

    public HotelServiceImpl(HotelRepository hotelRepository, ValidationUtilsImpl validationUtils)
    {this.hotelRepository = hotelRepository;
    this.validationUtils = validationUtils;}

    //Gets and filter the hotels
    @Override
    public List<HotelDTO> getHotels(FilterDTO hotelFilterDTO) throws ApiException {
        if(hotelFilterDTO != null){
            validationUtils.validateFilters(hotelFilterDTO);
        }
        List<HotelDTO> hotelDTOList = new ArrayList<HotelDTO>();
        hotelDTOList = hotelRepository.loadHotelsDB();
        hotelDTOList = hotelDTOList.stream()
                    .filter(p -> {
                        boolean matches = true;
                        if (hotelFilterDTO.getDestination() != null ) {
                            matches = !p.getBooked();
                            matches = matches && p.getDestination().equals(hotelFilterDTO.getDestination());
                        }
                        if (hotelFilterDTO.getDateFrom() != null) {
                            matches = matches && hotelFilterDTO.getDateFrom().compareTo(p.getDateFrom()) >= 0;
                        }
                        if (hotelFilterDTO.getDateTo() != null) {
                            matches = matches && hotelFilterDTO.getDateTo().compareTo(p.getDateTo()) <= 0;
                        }
                        return matches;
                    }).collect(Collectors.toList());
            if (hotelDTOList.size() == 0){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Hotels Not Found ");
            }
            else return hotelDTOList;
    }

    //Booking method: calls the validation method, the writing method and the interest calculator
    //form the booking response
    @Override
    public HotelReservationDTO bookHotel(HotelRequestDTO hotelRequestDTO) throws ApiException {

        List<HotelDTO> hotelDTOList = hotelRepository.loadHotelsDB();
        validationUtils.validateBookingRequest(hotelRequestDTO);

        long diff = hotelRequestDTO.getBooking().getDateTo().getTime()
                - hotelRequestDTO.getBooking().getDateFrom().getTime();
        long days = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);

        double pricePerNight = 0;
        for (HotelDTO hotelDTO : hotelDTOList) {
            if (hotelRequestDTO.getBooking().getHotelCode().equals(hotelDTO.getCode())) {
                pricePerNight = hotelDTO.getNightPrice();
                hotelRepository.bookHotel(hotelDTO);
            }
        }

        double interest = calculateInterest(hotelRequestDTO.getBooking().getPaymentMethod().getDues()
                ,hotelRequestDTO.getBooking().getPaymentMethod().getType());
        double amount = pricePerNight * days;
        double total = amount + (amount * interest);

        HotelResvationBookingDTO hotelResvationBookingDTO =
                new HotelResvationBookingDTO(hotelRequestDTO.getBooking().getDateFrom(),
                hotelRequestDTO.getBooking().getDateTo(),hotelRequestDTO.getBooking().getDestination(),
                hotelRequestDTO.getBooking().getHotelCode(),hotelRequestDTO.getBooking().getPeopleAmount(),
                hotelRequestDTO.getBooking().getRoomType(),hotelRequestDTO.getBooking().getPeople());
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO(200,"The Process Ended Correctly");

        return new HotelReservationDTO(hotelRequestDTO.getUserName(),
                amount,interest*100,total, hotelResvationBookingDTO,responseStatusDTO);
    }

    //calculates payment Interest
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
