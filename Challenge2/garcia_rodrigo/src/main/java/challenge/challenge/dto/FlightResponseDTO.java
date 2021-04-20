package challenge.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponseDTO {

    private String userName;
    private Double amount;
    private Double interest;
    private Double total;
    private FlightReservationResponseDTO flightReservation;
    private ResponseStatusDTO statusCode;
}
