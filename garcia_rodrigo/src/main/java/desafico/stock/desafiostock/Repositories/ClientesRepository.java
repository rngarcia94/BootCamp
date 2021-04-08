package desafico.stock.desafiostock.Repositories;

import desafico.stock.desafiostock.DTO.ClientesDTO;
import desafico.stock.desafiostock.Exception.ApiException;

import java.io.IOException;
import java.util.List;

public interface ClientesRepository {

    List<ClientesDTO> addClient(ClientesDTO clientesDTO) throws ApiException, IOException;

    List<ClientesDTO> filter(ClientesDTO filterDTO) throws ApiException;
}
