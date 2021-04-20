package challenge.challenge.utils;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.FlightDTO;
import challenge.challenge.dto.FlightRequestDTO;
import challenge.challenge.dto.HotelDTO;
import challenge.challenge.dto.HotelRequestDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtilsImpl implements FileUtils{

    private String filePath;

    public FileUtilsImpl (@Value("${path_file_hotelRequestTest}") String path){
        this.filePath = path;
    }

    //Reads a request in a json and returns a list
    @Override
    public HotelRequestDTO loadHotelRequest() {

        File file = null;

        try {
            file = ResourceUtils.getFile(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HotelRequestDTO> typeRef = new TypeReference<HotelRequestDTO>() {};
        HotelRequestDTO hotelRequestDTO = null;

        try {
            hotelRequestDTO = objectMapper.readValue(file, typeRef);
        }catch (Exception e){
            e.printStackTrace();
        }

        return hotelRequestDTO;
    }

    //Reads a request in a json and returns a list
    @Override
    public FlightRequestDTO loadFlightRequest() {

        File file = null;

        try {
            file = ResourceUtils.getFile(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<FlightRequestDTO> typeRef = new TypeReference<FlightRequestDTO>() {};
        FlightRequestDTO flightRequestDTO = null;

        try {
            flightRequestDTO = objectMapper.readValue(file, typeRef);
        }catch (Exception e){
            e.printStackTrace();
        }

        return flightRequestDTO;
    }

    //Reads all the information in a json and returns a list
    @Override
    public ArrayList<HotelDTO> loadHotelsDB() {
        File file = null;

        try {
            file = ResourceUtils.getFile(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<ArrayList<HotelDTO>> typeRef = new TypeReference<ArrayList<HotelDTO>>() {};
        ArrayList<HotelDTO> hotelDTO = null;

        try {
            hotelDTO = objectMapper.readValue(file, typeRef);
        }catch (Exception e){
            e.printStackTrace();
        }

        return hotelDTO;
    }

    //Reads all the information in a json and returns a list
    @Override
    public ArrayList<FlightDTO> loadFlightDB() {
        File file = null;

        try {
            file = ResourceUtils.getFile(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<ArrayList<FlightDTO>> typeRef = new TypeReference<ArrayList<FlightDTO>>() {};
        ArrayList<FlightDTO> flightDTO = null;

        try {
            flightDTO = objectMapper.readValue(file, typeRef);
        }catch (Exception e){
            e.printStackTrace();
        }

        return flightDTO;
    }

    // writes information back to the json
    @Override
    public void writeFile(String filePath, List<HotelDTO> hotelDTOList) throws ApiException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), hotelDTOList);
        } catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

}
