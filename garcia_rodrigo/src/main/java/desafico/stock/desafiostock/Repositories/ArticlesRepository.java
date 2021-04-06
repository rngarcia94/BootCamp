package desafico.stock.desafiostock.Repositories;

import desafico.stock.desafiostock.DTO.ArticleDTO;
import desafico.stock.desafiostock.DTO.ArticlesFilterDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ArticlesRepository {

    void loadAll();

    //Deprecado
    List<ArticleDTO> getArticles();

    //Deprecado
    List<ArticleDTO> getByFilter(String name);

    List<ArticleDTO> getFilteredByTwo(ArticlesFilterDTO filterDTO);

}
