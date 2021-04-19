package desafico.stock.desafiostock.Repositories;

import desafico.stock.desafiostock.DTO.*;
import desafico.stock.desafiostock.Exception.ApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ArticlesRepository {


    //carga todos los datos del csv a la lista
    void loadAll() throws ApiException;

    //se encarga de realizar las comparaciones y devolver los articulos encotrados
    List<ArticleDTO> getFilteredByTwo(ArticlesFilterDTO filterDTO) throws ApiException;

    // Se encarga de obtener los productos y pasarlos a un mapa basado en las ids enviadas en la compra
    HashMap<Integer,ArticleDTO> getByID(List<Integer> ids) throws ApiException;

    //actualiza los stock luego de realizada un compra
    void refreshStock(PurchaseOrderDTO purchaseOrderDTO) throws IOException;

    //busca todos lo articulos
    List<ArticleDTO> getAll();

}
