package linktracker.link_tracker.Services;

import linktracker.link_tracker.DTO.LinkDTO;
import linktracker.link_tracker.Exeptions.ApiException;
import linktracker.link_tracker.Repositories.LinkRepository;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class LinkServiceImpl implements LinkService{

    private LinkRepository linkRepository;
    public LinkServiceImpl(LinkRepository linkRepository){
        this.linkRepository = linkRepository;
    }

    @Override
    public Map<Integer, LinkDTO> storeLink(String link) throws ApiException {
        return linkRepository.storeLink(link);
    }

    @Override
    public String getLinkById(int id) throws ApiException {
        return linkRepository.getLinkById(id);
    }

    @Override
    public void addCountToLink(int id) throws ApiException {
        linkRepository.addCountToLink(id);
    }

    @Override
    public int getCountById(int id) throws ApiException{
        return linkRepository.getCountById(id);
    }

    @Override
    public Map<Integer,LinkDTO> invalidted(int id) throws ApiException{
        return linkRepository.invalidted(id);
    }

    @Override
    public Map<Integer, LinkDTO> protectedStoreLink(String link, String pass) throws ApiException{
        return linkRepository.protectedStoreLink(link,pass);
    }

    @Override
    public String getPassByID(int id) throws ApiException {
        return linkRepository.getPassByID(id);
    }
}
