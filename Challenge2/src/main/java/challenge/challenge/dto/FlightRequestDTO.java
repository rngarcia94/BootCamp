package challenge.challenge.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequestDTO {

    private String userName;
    private FlightReservationDTO flightReservation;
}
