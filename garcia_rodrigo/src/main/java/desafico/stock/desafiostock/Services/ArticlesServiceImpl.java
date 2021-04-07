package desafico.stock.desafiostock.Services;

import desafico.stock.desafiostock.DTO.*;
import desafico.stock.desafiostock.Exception.ApiException;
import desafico.stock.desafiostock.Repositories.ArticlesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticlesServiceImpl implements ArticleService{

    private int ticketId = 0;
    private ArticlesRepository articlesRepository;
    public ArticlesServiceImpl(ArticlesRepository articlesRepository){this.articlesRepository = articlesRepository;}

    //filtra y ordena los articulos
    @Override
    public List<ArticleDTO> getFilteredByTwo(ArticlesFilterDTO filterDTO) throws ApiException {
        //cuento la cantidad de parametros el order no lo tengo en cuenta
        int params = 0;
        if (filterDTO.getProductId() != null)params++;
        if (filterDTO.getName() != null) params++;
        if (filterDTO.getCategory() != null) params++;
        if (filterDTO.getFreeShipping() != null) params++;
        if (filterDTO.getBrand() != null) params++;
        if (filterDTO.getPrestige() != null) params++;
        if (filterDTO.getPrice() != null) params++;
        if (params < 3) {
            //ordeno
            List<ArticleDTO> sortedList = articlesRepository.getFilteredByTwo(filterDTO);
            if(sortedList.size() == 0)
                throw new ApiException(HttpStatus.BAD_REQUEST,"No se encontraron Articulos");
            if(filterDTO.getOrder() != null) {
                if (filterDTO.getOrder() == 0) {
                    sortedList.sort(Comparator.comparing(ArticleDTO::getName));
                    return sortedList;
                } else if (filterDTO.getOrder() == 1) {
                    sortedList.sort(Comparator.comparing(ArticleDTO::getName).reversed());
                    return sortedList;
                } else if (filterDTO.getOrder() == 2) {
                    sortedList.sort(Comparator.comparing(ArticleDTO::getPrice).reversed());
                    return sortedList;
                } else if (filterDTO.getOrder() == 3) {
                    sortedList.sort(Comparator.comparing(ArticleDTO::getPrice));
                    return sortedList;
                }

                else throw new ApiException(HttpStatus.BAD_REQUEST,"Parametro de filtro incorrecto");
            }
            else {
                return sortedList;
            }
        } else throw new ApiException(HttpStatus.BAD_REQUEST,"Demaciados Parametros Introducidos");
    }

    // Se encarga de la creacion de la compra y de devolver una respuesta
    @Override
    public ResponseDTO createPurchase(PurchaseOrderDTO purchaseOrder) throws ApiException, IOException {
        List<ArticlesForTicketDTO> articlesForTickets = purchaseOrder.getArticles();
        if(articlesForTickets.size() == 0)
            throw new ApiException(HttpStatus.BAD_REQUEST,"No ingreso articulos para comprar");
        List<Integer> ids = articlesForTickets.stream().map(ArticlesForTicketDTO::getProductId).collect(Collectors.toList());
        HashMap<Integer,ArticleDTO> articlesToPurchase = articlesRepository.getByID(ids);
        if(articlesToPurchase.size() == 0)
            throw new ApiException(HttpStatus.BAD_REQUEST,"No se encontraron articulos para comprar");

        double total = 0;
        List<Double> prices = new ArrayList<>();
        for (Map.Entry<Integer,ArticleDTO> entry:articlesToPurchase.entrySet()) {
                prices.add(entry.getValue().getPrice());
        }
        for (int i = 0; i < prices.size(); i++) {
            validateArticle(purchaseOrder.getArticles().get(i),articlesToPurchase);
            total += purchaseOrder.getArticles().get(i).getQuantity() * prices.get(i);
        }
        ticketId++;
        TicketDTO responseTicket = new TicketDTO(ticketId,purchaseOrder,total);
        StatusDTO responseStatus = new StatusDTO(200,"La solicitud de compra se completo con exito");
        // Actualizo el stock
        articlesRepository.refreshStock(purchaseOrder);
        return new ResponseDTO(responseTicket,responseStatus);
    }

    // Realiza las validaciones del proceso de compra
    private void validateArticle(ArticlesForTicketDTO articleInTicket, HashMap<Integer,ArticleDTO> articles) throws ApiException{
        if (articleInTicket.getQuantity() == null || articleInTicket.getQuantity() == 0){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Cantidad de Articulos Vacia " + articleInTicket.getName());
        }
        ArticleDTO articleToCompare = articles.get(articleInTicket.getProductId());
        if(articleToCompare == null){
            throw new ApiException(HttpStatus.BAD_REQUEST,"No se encotro el Producto " + articleInTicket.getName());
        }
        if(!articleInTicket.getBrand().equals(articleToCompare.getBrand())){
            throw new ApiException(HttpStatus.BAD_REQUEST,"No se encontro la Marca " + articleInTicket.getBrand());
        }
        if(!articleInTicket.getName().equals(articleToCompare.getName())){
            throw new ApiException(HttpStatus.BAD_REQUEST,"No se encontro la Nombre " + articleInTicket.getName());
        }
        if(articleInTicket.getQuantity() > articleToCompare.getQuantity()){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Stock Insuficiente del Producto " + articleInTicket.getName());
        }

    }
}
