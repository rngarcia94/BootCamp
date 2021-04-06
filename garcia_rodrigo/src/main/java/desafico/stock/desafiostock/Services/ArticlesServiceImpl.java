package desafico.stock.desafiostock.Services;

import desafico.stock.desafiostock.DTO.ArticleDTO;
import desafico.stock.desafiostock.DTO.ArticlesFilterDTO;
import desafico.stock.desafiostock.Repositories.ArticlesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticlesServiceImpl implements ArticleService{

    private ArticlesRepository articlesRepository;
    public ArticlesServiceImpl(ArticlesRepository articlesRepository){this.articlesRepository = articlesRepository;}

    //Deprecado
    @Override
    public List<ArticleDTO> getAll() {
        return articlesRepository.getArticles();
    }

    //Deprecado
    @Override
    public List<ArticleDTO> getByFilter(String name) {
        return articlesRepository.getByFilter(name);
    }

    //filtra y ordena los articulos -- Falta ordenar
    @Override
    public List<ArticleDTO> getFilteredByTwo(ArticlesFilterDTO filterDTO) {
        //cuento la cantidad de parametros el order no lo tengo en cuenta
        int params = 0;
        if (filterDTO.getName() != null) params++;
        if (filterDTO.getCategory() != null) params++;
        if (filterDTO.getFreeShipping() != null) params++;
        if (filterDTO.getBrand() != null) params++;
        if (filterDTO.getPrestige() != 0) params++;
        if (filterDTO.getPrice() != 0) params++;
        if (params < 3) {
            return articlesRepository.getFilteredByTwo(filterDTO);
        }
        //temporal debe tirar una excepcion
        System.out.println("demaciados parametros");
        return articlesRepository.getArticles();

    }
}
