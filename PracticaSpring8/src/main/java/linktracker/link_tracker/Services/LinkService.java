package linktracker.link_tracker.Services;

import linktracker.link_tracker.DTO.LinkDTO;
import linktracker.link_tracker.Exeptions.ApiException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public interface LinkService {

    Map<Integer, LinkDTO> storeLink(String link) throws ApiException;

    String getLinkById (int id) throws  ApiException;

    void addCountToLink(int id) throws ApiException;

    int getCountById(int id) throws ApiException;

    Map<Integer,LinkDTO> invalidted(int id) throws ApiException;

    Map<Integer,LinkDTO> protectedStoreLink(String link, String pass) throws ApiException;

    String getPassByID(int id) throws ApiException;

}
