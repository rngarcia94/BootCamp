package desafico.stock.desafiostock.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    int id;
    PurchaseOrderDTO articles;
    double total;

    public TicketDTO(PurchaseOrderDTO articles, double total) {
        this.articles = articles;
        this.total = total;
    }
}
