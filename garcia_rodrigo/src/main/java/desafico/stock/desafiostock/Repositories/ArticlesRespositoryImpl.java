package desafico.stock.desafiostock.Repositories;

import desafico.stock.desafiostock.DTO.*;
import desafico.stock.desafiostock.Exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ArticlesRespositoryImpl implements ArticlesRepository{

    private List<ArticleDTO> articles;

    //Lo utilizo para cargar el service antes que nada
    public ArticlesRespositoryImpl() throws ApiException{
        articles = new ArrayList<ArticleDTO>();
        loadAll();
    }

    //crea el path al archivo csv
    private File loadFile(String fileName){
        File file = new File("");
        try {
            file = ResourceUtils.getFile(file.getAbsolutePath()+ "/src/main/resources/" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    //carga todos los datos del csv a la lista
    @Override
    public void loadAll() throws ApiException {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            boolean firstline = true;
            br = new BufferedReader(new FileReader(loadFile("dbProductos.csv")));
            while ((line = br.readLine()) != null){
                if (!firstline) {
                    ArticleDTO articleDTO = new ArticleDTO();
                    String[] data = line.split(cvsSplitBy);
                    articleDTO.setProductId(Integer.parseInt(data[0]));
                    articleDTO.setName(data[1]);
                    articleDTO.setCategory(data[2]);
                    articleDTO.setBrand(data[3]);
                    String replaced = data[4].replace("$","").replace(".","");
                    articleDTO.setPrice(Double.parseDouble(replaced));
                    articleDTO.setQuantity(Integer.parseInt(data[5]));
                    articleDTO.setFreeShipping(data[6].equals("SI"));
                    articleDTO.setPrestige(data[7].length());
                    articles.add(articleDTO);
                }
                else firstline = false;
            }
        } catch (IOException e){
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

    @Override
    public List<ArticleDTO> getAll() {
        return articles;
    }
    //se encarga de realizar las comparaciones y devolver los articulos encotrados
    @Override
    public List<ArticleDTO> getFilteredByTwo(ArticlesFilterDTO filterDTO) throws ApiException {
        validateParams(filterDTO,articles);
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
                    if(filterDTO.price != null){
                        matches = matches && p.getPrice().equals(filterDTO.price);
                    }if(filterDTO.freeShipping != null){
                        matches = matches && p.getFreeShipping().equals(filterDTO.freeShipping);
                    }
                    if(filterDTO.prestige != null){
                        matches = matches && p.getPrestige().equals(filterDTO.prestige);
                    }
                    if(filterDTO.productId != null){
                        matches = matches && p.getProductId().equals(filterDTO.productId);
                    }
                    return matches;
                }).collect(Collectors.toList());
        if (articleDTOList.size() == 0){
            throw new ApiException(HttpStatus.BAD_REQUEST, "NO se encontraron articulos");
        }
        else return articleDTOList;
    }

    // Se encarga de obtener los productos y pasarlos a un mapa basado en las ids enviadas en la compra
    @Override
    public HashMap<Integer,ArticleDTO> getByID(List<Integer> ids) throws ApiException {
        HashMap<Integer,ArticleDTO> articlesFound = new HashMap<Integer,ArticleDTO>();
        for (ArticleDTO articleDTO: articles){
            if(ids.contains(articleDTO.getProductId())){
                articlesFound.put(articleDTO.getProductId(),articleDTO);
            }
        }
        if (articlesFound.size() == 0)
            throw new ApiException(HttpStatus.BAD_REQUEST,"No se Encontraron Articulos");
        else return articlesFound;
    }

    //actualiza los stock luego de realizada un compra, primero en memoria y luedo en el archivo
    @Override
    public void refreshStock(PurchaseOrderDTO purchaseOrderDTO) throws IOException {
        for (int i = 0; i < purchaseOrderDTO.getArticles().size(); i++) {
            for (ArticleDTO article : articles) {
                if (purchaseOrderDTO.getArticles().get(i).getProductId() == article.getProductId()) {
                    article.setQuantity(article.getQuantity() - purchaseOrderDTO.getArticles().get(i).getQuantity());
                }
            }
        }
        //llamo al metodo encargado de escribir el archivo
        saveDB(articles);
    }

    //Escribe en el archivo los cambios realizados en el stock
    public void saveDB(List<ArticleDTO> articles) throws IOException {
        FileWriter writer = new FileWriter(loadFile("dbProductos.csv"));

        String collect = "productId,name,category,brand,price,quantity,freeShipping;prestige\n";
        for(ArticleDTO article: articles) {
            String f = "NO";
            if(article.getFreeShipping()) f = "SI";
            collect += article.getProductId() + ","
                    + article.getName() + ","
                    + article.getCategory() + ","
                    + article.getBrand() + ","
                    + (int)Math.round(article.getPrice()) + ","
                    + article.getQuantity() + ","
                    + f + ","
                    + "*".repeat(Math.max(0, article.getPrestige())) +  "\n";
        }

        writer.write(collect);
        writer.flush();
        writer.close();
    }

    //Valida que los campos ingresados en los parametros sean validos, si no lanza una excepcion
    private void validateParams(ArticlesFilterDTO filters, List<ArticleDTO> articleDTOList) throws ApiException{
        if(filters.getName() != null){
            boolean f = true;
            for (ArticleDTO articleDTO : articleDTOList) {
                if (filters.getName().equals(articleDTO.getName())) f = false;
            }
            if (f) throw new ApiException(HttpStatus.BAD_REQUEST,"Nombre: " + filters.getName() + " no encontrado ");
        }
        if(filters.getBrand() != null){
            boolean f = true;
            for (ArticleDTO articleDTO : articleDTOList) {
                if (filters.getBrand().equals(articleDTO.getBrand())) f = false;
            }
            if (f) throw new ApiException(HttpStatus.BAD_REQUEST,"Marca " +filters.getBrand()+" no encontrada " );
        }
        if(filters.getCategory() != null){
            boolean f = true;
            for (ArticleDTO articleDTO : articleDTOList) {
                if (filters.getCategory().equals(articleDTO.getCategory())) f = false;
            }
            if (f) throw new ApiException(HttpStatus.BAD_REQUEST,"Categoria " +filters.getCategory()+" no encontrada " );
        }
        if(filters.getPrice() != null){
            boolean f = true;
            for (ArticleDTO articleDTO : articleDTOList) {
                if (filters.getPrice().equals (articleDTO.getPrice())) f = false;
            }
            if (f) throw new ApiException(HttpStatus.BAD_REQUEST,"Precio " +filters.getPrice()+" no encontrado " );
        }
        if(filters.getPrestige() != null) {
            if (filters.getPrestige() < 1 || filters.getPrestige() > 5) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Prestigio " + filters.getPrestige() + " no Valido");
            }
        }
        if(filters.getProductId() != null){
            boolean f = true;
            for (ArticleDTO articleDTO : articleDTOList) {
                if (filters.getProductId().equals (articleDTO.getProductId())) f = false;
            }
            if (f) throw new ApiException(HttpStatus.BAD_REQUEST,"ID " +filters.getProductId()+" no encontrado " );
        }
    }
}
