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

    TicketDTO responseTicket = new TicketDTO();
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
            //ordeno los productos antes de mostrarlos
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
        //Si no existe ticket anterior crea un nuevo
        if (ticketId == 0) {
            //valido la orden de compra
            for (int i = 0; i < purchaseOrder.getArticles().size(); i++) {
                validateArticle(purchaseOrder.getArticles().get(i),articlesRepository.getAll());
            }
            // Actualizo el stock para chequear que tengo stock disponible
            articlesRepository.refreshStock(purchaseOrder);
            //llamo al metodo encargado de crear el ticket
            responseTicket = createTicket(purchaseOrder);
        }
        // Si ya existe ticket lo actualiza con los nuevos productos
        else {
            //valido la orden de compra
            for (int i = 0; i < purchaseOrder.getArticles().size(); i++) {
                validateArticle(purchaseOrder.getArticles().get(i),articlesRepository.getAll());
            }
            // Actualizo el stock antes de comenzar para cheaquear que tengo stock disponible
            articlesRepository.refreshStock(purchaseOrder);
            //recorro el ticket para saber si el producto ya fue comprado si es asi sumo la nueva cantidad
            for (int i = 0; i < responseTicket.getArticles().getArticles().size(); i++) {
                boolean notFound = true;
                for (int j = 0; j < purchaseOrder.getArticles().size(); j++) {
                    if (purchaseOrder.getArticles().get(j).getProductId()
                            == (responseTicket.getArticles().getArticles().get(i).getProductId())) {
                        purchaseOrder.getArticles().get(j).setQuantity(purchaseOrder.getArticles().get(j).getQuantity()
                                + responseTicket.getArticles().getArticles().get(i).getQuantity());
                        notFound = false;
                    }
                }
                // si el producto no fue comprado lo agrego al ticket
                if (notFound){
                    purchaseOrder.getArticles().add(responseTicket.getArticles().getArticles().get(i));
                }
            }
            //llamo al metodo encargado de crear el ticket
            responseTicket = createTicket(purchaseOrder);
        }
        //creo el status y se lo agrego a la compra
        StatusDTO responseStatus = new StatusDTO(200,"La solicitud de compra se completo con exito");
        return new ResponseDTO(responseTicket,responseStatus);
    }

    // Realiza las validaciones del proceso de compra
    private void validateArticle(ArticlesForTicketDTO articleInTicket, List<ArticleDTO> articles) throws ApiException{
        if (articleInTicket.getQuantity() == null || articleInTicket.getQuantity() == 0){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Cantidad de Articulos Vacia " + articleInTicket.getName());
        }
        //el -1 es pq las posiciones en el get comienza en 0 y los productId en 1
        ArticleDTO articleToCompare = articles.get(articleInTicket.getProductId()-1);
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

    //creo el ticket de compra con los porductos solicitados
    private TicketDTO createTicket(PurchaseOrderDTO purchaseOrder) throws ApiException{
        //creo un nuevo ticket para devolverlo
        TicketDTO responseTicket = new TicketDTO();

        //busco los informacion completa de los articulos que desean comprar
        List<ArticlesForTicketDTO> articlesForTickets = purchaseOrder.getArticles();
        if (articlesForTickets.size() == 0)
            throw new ApiException(HttpStatus.BAD_REQUEST, "No ingreso articulos para comprar");
        List<Integer> ids = articlesForTickets.stream().map(ArticlesForTicketDTO::getProductId).collect(Collectors.toList());
        HashMap<Integer, ArticleDTO> articlesToPurchase = articlesRepository.getByID(ids);
        if (articlesToPurchase.size() == 0)
            throw new ApiException(HttpStatus.BAD_REQUEST, "No se encontraron articulos para comprar");

        //recorro los articulos para obtener sus precios
        double total = 0;
        List<Double> prices = new ArrayList<>();
        for (Map.Entry<Integer, ArticleDTO> entry : articlesToPurchase.entrySet()) {
            prices.add(entry.getValue().getPrice());
        }
        //calculo el total del ticket
        for (int i = 0; i < prices.size(); i++) {
            total += purchaseOrder.getArticles().get(i).getQuantity()
                    * articlesToPurchase.get(purchaseOrder.getArticles().get(i).getProductId()).getPrice();
        }
        //seteo los valores al ticket y lo retorno
        ticketId++;
        responseTicket.setId(ticketId);
        responseTicket.setArticles(purchaseOrder);
        responseTicket.setTotal(total);
        return responseTicket;
    }

}
