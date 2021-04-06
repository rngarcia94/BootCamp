package desafico.stock.desafiostock.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.PortUnreachableException;

@Data
@AllArgsConstructor
public class ArticlesFilterDTO {

    public String name;
    public String category;
    public String brand;
    public double price;
    public Boolean freeShipping;
    public int prestige;
    int order;

    public ArticlesFilterDTO(){}

}
