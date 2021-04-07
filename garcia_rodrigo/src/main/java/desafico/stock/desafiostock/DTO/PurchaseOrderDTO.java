package desafico.stock.desafiostock.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    List<ArticlesForTicketDTO> articles;
}
