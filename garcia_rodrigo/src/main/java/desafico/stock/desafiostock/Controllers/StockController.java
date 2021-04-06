package desafico.stock.desafiostock.Controllers;


import desafico.stock.desafiostock.DTO.ArticleDTO;
import desafico.stock.desafiostock.DTO.ArticlesFilterDTO;
import desafico.stock.desafiostock.DTO.OrderDTO;
import desafico.stock.desafiostock.Services.ArticleService;
import desafico.stock.desafiostock.Services.ArticlesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StockController {

    /*
    private final ArticleService articleService;

    // Equivalente a utilizar el Autowire
    public StockController(ArticlesServiceImpl articleService) {
        this.articleService = articlesService;
    }*/

    @Autowired
    private ArticlesServiceImpl articlesService;

    @GetMapping("/articles/")
    public ResponseEntity getFiltered(ArticlesFilterDTO filterDTO){
        return new ResponseEntity(articlesService.getFilteredByTwo(filterDTO),HttpStatus.OK);
    }

    @PostMapping("/purchase-request")
    public RequestParam purchase(@RequestBody OrderDTO buyOrder){
        return null;
    }


}
