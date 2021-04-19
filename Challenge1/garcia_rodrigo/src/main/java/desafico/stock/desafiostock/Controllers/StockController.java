package desafico.stock.desafiostock.Controllers;


import desafico.stock.desafiostock.DTO.ArticlesFilterDTO;
import desafico.stock.desafiostock.DTO.PurchaseOrderDTO;
import desafico.stock.desafiostock.DTO.TicketDTO;
import desafico.stock.desafiostock.Exception.ApiException;
import desafico.stock.desafiostock.Services.ArticlesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class StockController {

    @Autowired
    private ArticlesServiceImpl articlesService;

    //busca y traelos los articulos fucniona con o sin filtros
    @GetMapping("/articles/")
    public ResponseEntity getFiltered(ArticlesFilterDTO filterDTO) throws ApiException {
        return new ResponseEntity(articlesService.getFilteredByTwo(filterDTO),HttpStatus.OK);
    }

    //realiza la compra. Si envio varias peticiones seguida las suma todas en un mismo ticket y muestra el total
    @PostMapping("/purchase-request")
    public ResponseEntity purchase(@RequestBody PurchaseOrderDTO buyOrder) throws ApiException, IOException {
        return new ResponseEntity(articlesService.createPurchase(buyOrder),HttpStatus.OK);
    }


}
