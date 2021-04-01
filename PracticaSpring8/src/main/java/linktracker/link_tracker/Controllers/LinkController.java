package linktracker.link_tracker.Controllers;


import linktracker.link_tracker.Exeptions.ApiException;
import linktracker.link_tracker.Services.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkServiceImpl linkService;

    @PostMapping("/unprotected/")
    public ResponseEntity storeLink(@RequestParam String link ) throws ApiException {
        try {
            return new ResponseEntity(linkService.storeLink(link), HttpStatus.OK);
        }
        catch (ApiException e){
            throw new ApiException(HttpStatus.I_AM_A_TEAPOT,"El link Ingresado No es valido");
        }
    }

    @GetMapping("/unprotected/{linkId}")
    public RedirectView redirectView(@PathVariable int linkId) throws ApiException {
        try {
            linkService.addCountToLink(linkId);
            return new RedirectView(linkService.getLinkById(linkId));
        }
        catch (ApiException e){
            throw new ApiException(HttpStatus.I_AM_A_TEAPOT,"ID ingresado invalido");
        }
    }

    @GetMapping("/metrics/{linkId}")
    public ResponseEntity getMetrics(@PathVariable int linkId) throws ApiException{
        try {
            return new ResponseEntity(linkService.getCountById(linkId), HttpStatus.OK);
        }
        catch (ApiException e){
            throw new ApiException(HttpStatus.BAD_REQUEST, "ID Invalido ");
        }
    }

    @PostMapping("/invalidate/{linkId}")
    public ResponseEntity invalidateLink(@PathVariable int linkId) throws ApiException{
        try {
            return new ResponseEntity(linkService.invalidted(linkId), HttpStatus.OK);
        }
        catch (ApiException e){
            throw new ApiException(HttpStatus.BAD_REQUEST, "ID Invalido");
        }
    }

    @PostMapping("/")
    public ResponseEntity protectedStoreLink(@RequestParam String link, @RequestParam String pass) throws ApiException{
        try {
            return new ResponseEntity(linkService.protectedStoreLink(link, pass), HttpStatus.OK);
        }
        catch (ApiException e){
            throw new ApiException(HttpStatus.BAD_REQUEST, "ID Invalido");
        }
    }

    @GetMapping("/{linkId}/{pass}")
    public RedirectView protectedRedirectedView(@PathVariable int linkId, @PathVariable String pass) throws ApiException{
        try {
            if (linkService.getPassByID(linkId).equals(pass)) {
                linkService.addCountToLink(linkId);
                return new RedirectView(linkService.getLinkById(linkId));
            } else throw new ApiException(HttpStatus.FORBIDDEN, "Contraseña Incorrecta");
        }
        catch (ApiException e){
            throw new ApiException(HttpStatus.BAD_REQUEST, "ID invalido");
        }
    }
}
