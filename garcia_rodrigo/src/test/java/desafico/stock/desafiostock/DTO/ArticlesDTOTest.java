package desafico.stock.desafiostock.DTO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class ArticlesDTOTest extends DTOTestFiles {

    public static ArrayList<ArticleDTO> getTestProducts() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(createFile("testProducts.json"), new TypeReference<ArrayList<ArticleDTO>>() {
        });
    }

    public static ArrayList<ArticleDTO> getExpectedProductsCategoryHerramientas() {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        list.add(new ArticleDTO(1, "Desmalezadora", "Makita", 5, 9600.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(2, "Taladro", "Black & Decker", 7, 12500.0, "Herramientas", true, 4));
        return list;
    }

    public static ArrayList<ArticleDTO> getProductsForCategoryAndFreeShipmentFilter() {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        list.add(new ArticleDTO(1, "Desmalezadora", "Makita", 5, 9600.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(2, "Taladro", "Black & Decker", 7, 12500.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(3, "Soldadora", "Makita", 5, 9600.0, "Herramientas", false, 4));
        list.add(new ArticleDTO(4, "Samsung Galaxy s21 Ultra", "Samsung", 7, 150000.0, "Celulares", true, 4));
        list.add(new ArticleDTO(5, "Samsung Galaxy s21 +", "Samsung", 7, 130000.0, "Celulares", true, 4));
        list.add(new ArticleDTO(6, "Samsung Galaxy s21", "Samsung", 7, 100000.0, "Celulares", true, 4));
        list.add(new ArticleDTO(7, "Short", "Indumentaria", 10, 2400.0, "Indumentaria", true, 4));
        list.add(new ArticleDTO(8, "Remerita", "Indumentaria", 20, 1500.0, "Indumentaria", true, 4));
        return list;
    }

    public static ArrayList<ArticleDTO> getExpectedProductsForCategoryAndFreeShippingFilter() {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        list.add(new ArticleDTO(1, "Desmalezadora", "Makita", 5, 9600.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(2, "Taladro", "Black & Decker", 7, 12500.0, "Herramientas", true, 4));
        return list;
    }

    public static ArrayList<ArticleDTO> getProductsAlphabeticallyDesordered() {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        list.add(new ArticleDTO(1, "Desmalezadora", "Makita", 5, 9600.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(2, "Taladro", "Black & Decker", 7, 12500.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(4, "Samsung Galaxy s21 Ultra", "Samsung", 7, 150000.0, "Celulares", true, 4));
        list.add(new ArticleDTO(3, "Soldadora", "Makita", 5, 9600.0, "Herramientas", false, 4));
        return list;
    }

    public static ArrayList<ArticleDTO> getProductsAlphabeticallyOrderedDes() {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        list.add(new ArticleDTO(2, "Taladro", "Black & Decker", 7, 12500.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(3, "Soldadora", "Makita", 5, 9600.0, "Herramientas", false, 4));
        list.add(new ArticleDTO(4, "Samsung Galaxy s21 Ultra", "Samsung", 7, 150000.0, "Celulares", true, 4));
        list.add(new ArticleDTO(1, "Desmalezadora", "Makita", 5, 9600.0, "Herramientas", true, 4));
        return list;
    }

    public static ArrayList<ArticleDTO> getProductsAlphabeticallyOrderedAsc() {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        list.add(new ArticleDTO(1, "Desmalezadora", "Makita", 5, 9600.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(4, "Samsung Galaxy s21 Ultra", "Samsung", 7, 150000.0, "Celulares", true, 4));
        list.add(new ArticleDTO(3, "Soldadora", "Makita", 5, 9600.0, "Herramientas", false, 4));
        list.add(new ArticleDTO(2, "Taladro", "Black & Decker", 7, 12500.0, "Herramientas", true, 4));
        return list;
    }

    public static ArrayList<ArticleDTO> getProductsPriceDesordered() {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        list.add(new ArticleDTO(1, "Desmalezadora", "Makita", 5, 9600.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(2, "Taladro", "Black & Decker", 7, 12500.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(3, "Soldadora", "Makita", 5, 100.0, "Herramientas", false, 4));
        list.add(new ArticleDTO(4, "Samsung Galaxy s21 Ultra", "Samsung", 7, 150000.0, "Celulares", true, 4));
        return list;
    }

    public static ArrayList<ArticleDTO> getProductsPriceOrderedLowerToHigher() {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        list.add(new ArticleDTO(3, "Soldadora", "Makita", 5, 100.0, "Herramientas", false, 4));
        list.add(new ArticleDTO(1, "Desmalezadora", "Makita", 5, 9600.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(2, "Taladro", "Black & Decker", 7, 12500.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(4, "Samsung Galaxy s21 Ultra", "Samsung", 7, 150000.0, "Celulares", true, 4));
        return list;
    }

    public static ArrayList<ArticleDTO> getProductsPriceOrderedHigherToLower() {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        list.add(new ArticleDTO(4, "Samsung Galaxy s21 Ultra", "Samsung", 7, 150000.0, "Celulares", true, 4));
        list.add(new ArticleDTO(2, "Taladro", "Black & Decker", 7, 12500.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(1, "Desmalezadora", "Makita", 5, 9600.0, "Herramientas", true, 4));
        list.add(new ArticleDTO(3, "Soldadora", "Makita", 5, 100.0, "Herramientas", false, 4));
        return list;
    }

    public static PurchaseOrderDTO getPurchaseOrder(){
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        ArrayList<ArticlesForTicketDTO> list = new ArrayList<>();
        list.add(new ArticlesForTicketDTO(1,"Desmalezadora","Makita",2));
        list.add(new ArticlesForTicketDTO(3,"Soldadora","Makita",2));
        purchaseOrderDTO.setArticles(list);
        return purchaseOrderDTO;
    }

    public static ResponseDTO getExpectedTicket(){
        TicketDTO ticketDTO = new TicketDTO();
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        ArrayList<ArticlesForTicketDTO> list = new ArrayList<>();
        list.add(new ArticlesForTicketDTO(1,"Desmalezadora","Makita",2));
        list.add(new ArticlesForTicketDTO(3,"Soldadora","Makita",2));
        purchaseOrderDTO.setArticles(list);
        ticketDTO.setId(1);
        ticketDTO.setArticles(purchaseOrderDTO);
        ticketDTO.setTotal(19400);
        StatusDTO statusDTO = new StatusDTO(200,"La solicitud de compra se completo con exito");
        return new ResponseDTO(ticketDTO,statusDTO);
    }

    public static PurchaseOrderDTO getBadPurchaseOrder(){
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        ArrayList<ArticlesForTicketDTO> list = new ArrayList<>();
        list.add(new ArticlesForTicketDTO(1,"Desmalezadora","Makita",2));
        list.add(new ArticlesForTicketDTO(9,"Soldadora","Makita",2));
        purchaseOrderDTO.setArticles(list);
        return purchaseOrderDTO;
    }
}
