package desafico.stock.desafiostock.Repositories;

import desafico.stock.desafiostock.DTO.ClientesDTO;
import desafico.stock.desafiostock.Exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientesRepositoryImpl implements ClientesRepository{

    private List<ClientesDTO> clientesDTOList;

    //carga el archivo apenas comienza
    public ClientesRepositoryImpl() {
        clientesDTOList = new ArrayList<>();
        loadAll();
    }

    //Crea un nuevo cliente
    @Override
    public List<ClientesDTO> addClient(ClientesDTO clientesDTO) throws ApiException, IOException {
        validateClient(clientesDTO);
        clientesDTOList.add(clientesDTO);
        saveDB(clientesDTOList);
        return clientesDTOList;
    }

    //Filtra los clientes por cualqueir parametro o multiples parametros
    @Override
    public List<ClientesDTO> filter(ClientesDTO filterDTO) throws ApiException {
        validateParams(filterDTO);
        List<ClientesDTO> clientesList = clientesDTOList;
        clientesList = clientesList.stream()
                .filter(p -> {
                    boolean matches = true;
                    if(filterDTO.getDni() != null){
                        matches = matches && p.getDni().equals(filterDTO.getDni());
                    }
                    if(filterDTO.getName() != null){
                        matches = matches && p.getName().equals(filterDTO.getName());
                    }
                    if(filterDTO.getLastName() != null){
                        matches = matches && p.getLastName().equals(filterDTO.getLastName());
                    }
                    if(filterDTO.getState() != null){
                        matches = matches && p.getState().equals(filterDTO.getState());
                    }
                    return matches;
                }).collect(Collectors.toList());
        if (clientesList.size() == 0){
            throw new ApiException(HttpStatus.BAD_REQUEST,"No se encontraron Clientes");
        }
        else return clientesList;
    }

    //valida los campos de un nuevo cliente
    private void validateClient(ClientesDTO clientesDTO) throws ApiException {
        if(clientesDTO.getDni() == null){
            throw new ApiException(HttpStatus.BAD_REQUEST,"No ingreso DNI");
        }else {
            for (ClientesDTO dto : clientesDTOList) {
                if (clientesDTO.getDni().equals(dto.getDni())) {
                    throw new ApiException(HttpStatus.CONFLICT,"DNI ya registrado");
                }
            }
        }
        if(clientesDTO.getName() == null){
            throw new ApiException(HttpStatus.BAD_REQUEST,"No ingreso Nombre");
        }
        if(clientesDTO.getLastName() == null){
            throw new ApiException(HttpStatus.BAD_REQUEST,"No ingreso Apellido");
        }
        if(clientesDTO.getState() == null){
            throw new ApiException(HttpStatus.BAD_REQUEST,"No ingreso Provincia");
        }
    }

    //valida los parametros de busqueda
    private void validateParams(ClientesDTO filters) throws ApiException{
        if(filters.getDni() != null){
            boolean f = true;
            for (ClientesDTO clientesDTO : clientesDTOList) {
                if (filters.getDni().equals(clientesDTO.getDni())) f = false;
            }
            if (f) throw new ApiException(HttpStatus.BAD_REQUEST,"Dni: " + filters.getDni() + " no encontrado");
        }
        if(filters.getName() != null){
            boolean f = true;
            for (ClientesDTO clientesDTO : clientesDTOList) {
                if (filters.getName().equals(clientesDTO.getName())) f = false;
            }
            if (f) throw new ApiException(HttpStatus.BAD_REQUEST,"Nobmre: " + filters.getName() + " no encontrado");
        }
        if(filters.getLastName() != null){
            boolean f = true;
            for (ClientesDTO clientesDTO : clientesDTOList) {
                if (filters.getLastName().equals(clientesDTO.getLastName())) f = false;
            }
            if (f) throw new ApiException(HttpStatus.BAD_REQUEST,"Apellido: " + filters.getLastName() + " no encontrado");
        }
        if(filters.getState() != null){
            boolean f = true;
            for (ClientesDTO clientesDTO : clientesDTOList) {
                if (filters.getState().equals(clientesDTO.getState())) f = false;
            }
            if (f) throw new ApiException(HttpStatus.BAD_REQUEST,"Provincia: " + filters.getState() + " no encontrada");
        }
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


    //leo el archivo y cargo todos los cliente que tengo
    private void loadAll(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            boolean firtline = true;
            br = new BufferedReader(new FileReader(loadFile("dbClientes.csv")));
            while ((line = br.readLine()) != null){
                if(!firtline){
                    ClientesDTO clientesDTO = new ClientesDTO();
                    String[] data = line.split(cvsSplitBy);
                    clientesDTO.setDni(Integer.parseInt(data[0]));
                    clientesDTO.setName(data[1]);
                    clientesDTO.setLastName(data[2]);
                    clientesDTO.setState(data[3]);
                    clientesDTOList.add(clientesDTO);
                }
                else firtline = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //se encarga de guardar los cambios en la DB
    private void saveDB(List<ClientesDTO> clientes) throws IOException{
        FileWriter writer = new FileWriter(loadFile("dbClientes.csv"));
        String collect = "dni,name,lastName,state\n";
        for(ClientesDTO cliente:clientes){
            collect += cliente.getDni() + "," +
                    cliente.getName() + "," +
                    cliente.getLastName() + "," +
                    cliente.getState() + "\n";
        }

        writer.write(collect);
        writer.flush();
        writer.close();
    }
}
