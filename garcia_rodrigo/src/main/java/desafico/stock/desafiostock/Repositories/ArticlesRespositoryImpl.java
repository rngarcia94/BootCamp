package desafico.stock.desafiostock.Repositories;

import desafico.stock.desafiostock.DTO.ArticleDTO;
import desafico.stock.desafiostock.DTO.ArticlesFilterDTO;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ArticlesRespositoryImpl implements ArticlesRepository{

    private final List<ArticleDTO> articles;

    //Lo utilizo para cargar el service antes que nada
    public ArticlesRespositoryImpl(){
        articles = new ArrayList<ArticleDTO>();
        loadAll();
    }

    //carga todos los datos del csv a la lista
    @Override
    public void loadAll() {
        String csvFile = "/Users/rodgarcia/Desktop/Bootcamp/garcia_rodrigo/src/main/resources/dbProductos.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            boolean firstline = true;
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null){
                if (!firstline) {
                    ArticleDTO articleDTO = new ArticleDTO();
                    String[] data = line.split(cvsSplitBy);
                    articleDTO.setProductID(Integer.parseInt(data[0]));
                    articleDTO.setName(data[1]);
                    articleDTO.setCategory(data[2]);
                    articleDTO.setBrand(data[3]);
                    String replaced = data[4].replace("$","").replace(".","");
                    articleDTO.setPrice(Double.parseDouble(replaced));
                    articleDTO.setQuantity(Integer.parseInt(data[5]));
                    articleDTO.setFreeShipping(Boolean.parseBoolean(data[6]));
                    articleDTO.setPrestige(data[7].length());
                    articles.add(articleDTO);
                }
                else firstline = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Deprecado
    @Override
    public List<ArticleDTO> getArticles() {
        return articles;
    }

    //Deprecado
    @Override
    public List<ArticleDTO> getByFilter(String name) {
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        articleDTOList = articles;
        articleDTOList = articleDTOList.stream()
                .filter(p -> p.getCategory().equals(name)).collect(Collectors.toList());
        return articleDTOList;
    }

    //se encarga de realizar las comparaciones
    @Override
    public List<ArticleDTO> getFilteredByTwo(ArticlesFilterDTO filterDTO) {
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        articleDTOList = articles;
        articleDTOList = articleDTOList.stream()
                .filter(p -> {
                    boolean matches = true;
                    if(filterDTO.name != null){
                        matches = matches && p.getName().equals(filterDTO.name);
                    }
                    if(filterDTO.brand != null){
                        matches = matches && p.getBrand().equals(filterDTO.brand);
                    }
                    if(filterDTO.category != null){
                        matches = matches && p.getCategory().equals(filterDTO.category);
                    }
                    if(filterDTO.price != 0){
                        matches = matches && p.getPrice() == (filterDTO.price);
                    }if(filterDTO.freeShipping != null){
                        matches = matches && p.getFreeShipping().equals(filterDTO.freeShipping);
                    }
                    if(filterDTO.prestige != 0){
                        matches = matches && p.getPrestige() == (filterDTO.prestige);
                    }
                    return matches;
                }).collect(Collectors.toList());
        return articleDTOList;
    }

}
