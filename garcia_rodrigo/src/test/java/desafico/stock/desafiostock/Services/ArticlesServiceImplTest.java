package desafico.stock.desafiostock.Services;

import desafico.stock.desafiostock.DTO.*;
import desafico.stock.desafiostock.Exception.ApiError;
import desafico.stock.desafiostock.Exception.ApiException;
import desafico.stock.desafiostock.Repositories.ArticlesRepository;
import desafico.stock.desafiostock.Repositories.ArticlesRespositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(ArticleService.class)
class ArticlesServiceImplTest {

    @MockBean
    ArticlesRepository articlesRepository;
    @MockBean
    ArticleService articleService;

    private ArrayList<ArticleDTO> articles;

    @BeforeEach
    public void setUp() throws IOException, ApiException {
        articlesRepository = new ArticlesRespositoryImpl(ArticlesDTOTest.getTestProducts());
        articleService = new ArticlesServiceImpl(articlesRepository);
        articles = ArticlesDTOTest.getTestProducts();
    }


    @Test
    void getAllArticlesNoFilters_ShouldReturnOK() throws ApiException, IOException {
        ArticlesFilterDTO filter = new ArticlesFilterDTO();
        List<ArticleDTO> result = articleService.getFilteredByTwo(filter);
        assertNotNull(result,"The list is empty");
        assertEquals(articles,result);

    }

    @Test
    void get2FilteredBy1_ShouldReturnOK() throws ApiException, IOException {

        ArticlesFilterDTO filter = new ArticlesFilterDTO();
        filter.setCategory("Herramientas");

        List<ArticleDTO> result = articlesRepository.getFilteredByTwo(filter);
        ArrayList<ArticleDTO> expected = ArticlesDTOTest.getExpectedProductsCategoryHerramientas();
        assertNotNull(result,"The list is empty");
        assertEquals(expected,result);

    }

    @Test
    void getFilteredBy2_ShouldReturnOK() throws ApiException {
        articlesRepository = new ArticlesRespositoryImpl(ArticlesDTOTest.getProductsForCategoryAndFreeShipmentFilter());
        articleService = new ArticlesServiceImpl(articlesRepository);

        ArticlesFilterDTO filter = new ArticlesFilterDTO();
        filter.setCategory("Herramientas");
        filter.setFreeShipping(true);

        List<ArticleDTO> result = articlesRepository.getFilteredByTwo(filter);
        ArrayList<ArticleDTO> expected = ArticlesDTOTest.getExpectedProductsForCategoryAndFreeShippingFilter();

        assertNotNull(result,"The list is empty");
        assertEquals(expected,result);
    }

    @Test
    void getOrderedDesc_ShouldReturnOK() throws ApiException {
        articlesRepository = new ArticlesRespositoryImpl(ArticlesDTOTest.getProductsAlphabeticallyDesordered());
        articleService = new ArticlesServiceImpl(articlesRepository);

        ArticlesFilterDTO filter = new ArticlesFilterDTO();
        filter.setOrder(1);

        List<ArticleDTO> result = articleService.getFilteredByTwo(filter);
        ArrayList<ArticleDTO> expected = ArticlesDTOTest.getProductsAlphabeticallyOrderedDes();

        assertNotNull(result,"The list is empty");
        assertEquals(expected,result);
    }

    @Test
    void getOrderedAsc_ShouldReturnOK() throws ApiException {
        articlesRepository = new ArticlesRespositoryImpl(ArticlesDTOTest.getProductsAlphabeticallyDesordered());
        articleService = new ArticlesServiceImpl(articlesRepository);

        ArticlesFilterDTO filter = new ArticlesFilterDTO();
        filter.setOrder(0);

        List<ArticleDTO> result = articleService.getFilteredByTwo(filter);
        ArrayList<ArticleDTO> expected = ArticlesDTOTest.getProductsAlphabeticallyOrderedAsc();

        assertNotNull(result,"The list is empty");
        assertEquals(expected,result);
    }

    @Test
    void getOrderedHighPrice_ShouldReturnOK() throws ApiException {
        articlesRepository = new ArticlesRespositoryImpl(ArticlesDTOTest.getProductsPriceDesordered());
        articleService = new ArticlesServiceImpl(articlesRepository);

        ArticlesFilterDTO filter = new ArticlesFilterDTO();
        filter.setOrder(2);

        List<ArticleDTO> result = articleService.getFilteredByTwo(filter);
        ArrayList<ArticleDTO> expected = ArticlesDTOTest.getProductsPriceOrderedHigherToLower();

        assertNotNull(result,"The list is empty");
        assertEquals(expected,result);
    }

    @Test
    void getOrderedLowPrice_ShouldReturnOK() throws ApiException {
        articlesRepository = new ArticlesRespositoryImpl(ArticlesDTOTest.getProductsPriceDesordered());
        articleService = new ArticlesServiceImpl(articlesRepository);

        ArticlesFilterDTO filter = new ArticlesFilterDTO();
        filter.setOrder(3);

        List<ArticleDTO> result = articleService.getFilteredByTwo(filter);
        ArrayList<ArticleDTO> expected = ArticlesDTOTest.getProductsPriceOrderedLowerToHigher();

        assertNotNull(result,"The list is empty");
        assertEquals(expected,result);
    }

    @Test
    void purchaseArticles_ShouldReturnOK() throws ApiException, IOException {
        articlesRepository = new ArticlesRespositoryImpl(ArticlesDTOTest.getProductsPriceDesordered());
        articleService = new ArticlesServiceImpl(articlesRepository);

        ResponseDTO result = articleService.createPurchase(ArticlesDTOTest.getPurchaseOrder());
        ResponseDTO expected = ArticlesDTOTest.getExpectedTicket();

        assertNotNull(result,"The list is empty");
        assertEquals(expected,result);
    }

    @Test
    void purchaseArticles_ShouldReturnBad_Request() throws ApiException, IOException {
       /* articlesRepository = new ArticlesRespositoryImpl(ArticlesDTOTest.getProductsPriceDesordered());
        articleService = new ArticlesServiceImpl(articlesRepository);

        ApiException result = articleService.createPurchase(ArticlesDTOTest.getBadPurchaseOrder());

        assertNotNull(result,"The list is empty");
        assertEquals(new ApiException(HttpStatus.BAD_REQUEST,"No se encotro el Producto Soldadora"),result);
    */
    }
}