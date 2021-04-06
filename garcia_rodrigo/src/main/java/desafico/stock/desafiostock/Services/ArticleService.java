package desafico.stock.desafiostock.Services;

import desafico.stock.desafiostock.DTO.ArticleDTO;
import desafico.stock.desafiostock.DTO.ArticlesFilterDTO;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    //Deprecado
    List<ArticleDTO> getAll();

    //Deprecado
    List<ArticleDTO> getByFilter(String name);

    List<ArticleDTO> getFilteredByTwo(ArticlesFilterDTO filterDTO);


}
