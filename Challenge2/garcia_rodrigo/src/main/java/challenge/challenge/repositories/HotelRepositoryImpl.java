package challenge.challenge.repositories;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.HotelDTO;
import challenge.challenge.dto.HotelRequestDTO;
import challenge.challenge.utils.FileUtils;
import challenge.challenge.utils.FileUtilsImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HotelRepositoryImpl implements HotelRepository{

    private String filePath;
    private FileUtils fileUtils;

    public HotelRepositoryImpl (@Value("${path_file_hotel}") String path){
        this.filePath = path;
    }

    //calls the readind file method fro FileUtils
    @Override
    public ArrayList<HotelDTO> loadHotelsDB() {
        fileUtils = new FileUtilsImpl(filePath);
        return fileUtils.loadHotelsDB();
    }

    /**
     * Set the booked attribute to the hotel indicated
     * @param hotelToBook Hotel to book
     * @throws ApiException when read or write file fails
     */
    @Override
    public void bookHotel(HotelDTO hotelToBook) throws ApiException {
        List<HotelDTO> list = loadHotelsDB();
        int index = list.indexOf(hotelToBook);
        list.get(index).setBooked(true);
        updateDB(list);
    }

    //writes the changes in the db
    private void updateDB(List<HotelDTO> listUpdated) throws ApiException {
        fileUtils = new FileUtilsImpl(filePath);
        fileUtils.writeFile(filePath, listUpdated);
    }


}
