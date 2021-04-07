package desafico.stock.desafiostock.Services;

import desafico.stock.desafiostock.DTO.*;
import desafico.stock.desafiostock.Exception.ApiException;

import java.io.IOException;
import java.util.List;

public interface ArticleService {

    //filtra y ordena los articulos
    List<ArticleDTO> getFilteredByTwo(ArticlesFilterDTO filterDTO) throws ApiException;

    // Se encarga de la creacion de la compra y de devolver una respuesta
    ResponseDTO createPurchase(PurchaseOrderDTO purchaseOrderDTO) throws ApiException, IOException;



}
