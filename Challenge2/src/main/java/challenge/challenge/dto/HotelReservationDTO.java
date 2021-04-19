package challenge.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelReservationDTO {

    private String userName;
    private Double amount;
    private Double interest;
    private Double total;
    private HotelResvationBookingDTO booking;
    private ResponseStatusDTO statusCode;
}
