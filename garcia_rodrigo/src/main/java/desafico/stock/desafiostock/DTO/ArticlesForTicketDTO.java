package desafico.stock.desafiostock.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesForTicketDTO{

    int productId;
    String name;
    String brand;
    Integer quantity;
}
