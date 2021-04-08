package desafico.stock.desafiostock.Controllers;

import desafico.stock.desafiostock.DTO.ClientesDTO;
import desafico.stock.desafiostock.Exception.ApiException;
import desafico.stock.desafiostock.Services.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClienteServiceImpl clienteService;

    //Permite agegar clientes atravez de un JSON
    @PostMapping("")
    public ResponseEntity addClient(@RequestBody ClientesDTO client) throws ApiException, IOException {
        return new ResponseEntity(clienteService.addClient(client), HttpStatus.OK);
    }

    //Permite traer todos los cliente con o sin filtros
    @GetMapping("")
    public ResponseEntity getClient(ClientesDTO filterDTO) throws ApiException{
        return new ResponseEntity(clienteService.filter(filterDTO),HttpStatus.OK);
    }
}
