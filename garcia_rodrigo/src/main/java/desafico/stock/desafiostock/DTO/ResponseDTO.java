package desafico.stock.desafiostock.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    TicketDTO ticket;
    StatusDTO status;
}
