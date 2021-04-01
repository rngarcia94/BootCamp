package linktracker.link_tracker.Repositories;

import linktracker.link_tracker.DTO.LinkDTO;
import linktracker.link_tracker.Exeptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LinkRepositoryImpl implements LinkRepository{

    private Map<Integer,LinkDTO> linkMap = new HashMap<Integer,LinkDTO>();
    private int mapIndex = 0;

    @Override
    public Map<Integer, LinkDTO> storeLink(String link) throws ApiException {
        if(link.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")){
            LinkDTO linkDTO = new LinkDTO(link,0);
            this.linkMap.put(mapIndex, linkDTO);
            mapIndex++;
            return linkMap;
        }
        else throw new ApiException(HttpStatus.BAD_REQUEST,"URL Invalida");
    }

    @Override
    public String getLinkById (int id) throws ApiException{
        return linkMap.get(id).getLink();
    }

    @Override
    public void addCountToLink(int id) throws ApiException{
        linkMap.get(id).setCount(linkMap.get(id).getCount() + 1);
    }

    @Override
    public int getCountById(int id) throws ApiException {
        return linkMap.get(id).getCount();
    }

    @Override
    public Map<Integer,LinkDTO> invalidted(int id) throws ApiException {
        linkMap.remove(id);
        return linkMap;
    }

    @Override
    public Map<Integer, LinkDTO> protectedStoreLink(String link, String pass)throws ApiException {
        if(link.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")){
            LinkDTO linkDTO = new LinkDTO(link,0, pass);
            this.linkMap.put(mapIndex, linkDTO);
            mapIndex++;
            return linkMap;
        }
        else throw new ApiException(HttpStatus.BAD_REQUEST,"URL Invalida");
    }

    @Override
    public String getPassByID(int id) throws ApiException {
        return linkMap.get(id).getPass();
    }
}
