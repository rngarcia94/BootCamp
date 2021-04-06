package desafico.stock.desafiostock.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    int productID;
    String name;
    String brand;
    int quantity;
    double price;
    String category;
    Boolean freeShipping;
    int prestige;

}
