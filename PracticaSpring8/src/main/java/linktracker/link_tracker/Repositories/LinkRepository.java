package linktracker.link_tracker.Repositories;

import linktracker.link_tracker.DTO.LinkDTO;
import linktracker.link_tracker.Exeptions.ApiException;

import java.util.Map;

public interface LinkRepository {

    Map<Integer,LinkDTO> storeLink(String name) throws ApiException;

    String getLinkById (int id) throws ApiException;

    void addCountToLink(int id) throws ApiException;

    int getCountById(int id) throws ApiException;

    Map<Integer,LinkDTO> invalidted(int id) throws ApiException;

    Map<Integer,LinkDTO> protectedStoreLink(String link, String pass) throws ApiException;

    String getPassByID(int id) throws ApiException;
}
