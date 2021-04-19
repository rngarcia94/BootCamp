package desafico.stock.desafiostock.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesDTO {

    private Integer dni;
    private String name;
    private String lastName;
    private String state;
}
