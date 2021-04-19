package desafico.stock.desafiostock.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    Integer productId;
    String name;
    String brand;
    int quantity;
    Double price;
    String category;
    Boolean freeShipping;
    Integer prestige;

}
