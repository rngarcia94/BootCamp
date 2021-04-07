package desafico.stock.desafiostock.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.PortUnreachableException;

@Data
@AllArgsConstructor
public class ArticlesFilterDTO {

    public Integer productId;
    public String name;
    public String category;
    public String brand;
    public Double price;
    public Boolean freeShipping;
    public Integer prestige;
    public Integer order;

    public ArticlesFilterDTO(){}

}
