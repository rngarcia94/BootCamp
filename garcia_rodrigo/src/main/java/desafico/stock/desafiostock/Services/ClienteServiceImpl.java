package desafico.stock.desafiostock.Services;

import desafico.stock.desafiostock.DTO.ClientesDTO;
import desafico.stock.desafiostock.Exception.ApiException;
import desafico.stock.desafiostock.Repositories.ClientesRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    private ClientesRepository clientesRepository;
    public ClienteServiceImpl(ClientesRepository clientesRepository){this.clientesRepository = clientesRepository;}

    @Override
    public List<ClientesDTO> addClient(ClientesDTO clientesDTO) throws ApiException, IOException {
        return clientesRepository.addClient(clientesDTO);
    }

    @Override
    public List<ClientesDTO> filter(ClientesDTO filterDTO) throws ApiException{
        return clientesRepository.filter(filterDTO);
    }
}
