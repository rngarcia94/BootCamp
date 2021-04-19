package challenge.challenge.repositories;

import challenge.challenge.Exception.ApiException;
import challenge.challenge.dto.FlightDTO;
import challenge.challenge.dto.HotelDTO;
import challenge.challenge.utils.FileUtils;
import challenge.challenge.utils.FileUtilsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepositoryImpl implements FlightRepository{

    private String filePath;
    private FileUtils fileUtils;

    public FlightRepositoryImpl (@Value("${path_file_Flight}") String path){
        this.filePath = path;
    }

    //Calls the reading file method form FileUtils
    @Override
    public ArrayList<FlightDTO> loadFlightDB() {
        fileUtils = new FileUtilsImpl(filePath);
        return fileUtils.loadFlightDB();
    }

}
