package challenge.challenge.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightTestHelper {

    public List<FlightDTO> getTestFlights(){
        List<FlightDTO> list = new ArrayList<>();
        list.add(new FlightDTO("BOCA-4213",
                 "Bogotá",
                 "Cartagena",
                "Economy",
                8000.0,
                new Date(121,0,23),
                 new Date(121,1,5)));
        list.add((new FlightDTO( "CAME-0321",
                 "Cartagena",
                 "Medellín",
                 "Economy",
                 7800.0,
                 new Date(121,0,23),
                new Date(121,0,31))));
        list.add(new FlightDTO("BOBA-6567",
                 "Bogotá",
                "Buenos Aires",
                "Business",
                 57000.0,
                new Date(121,1,15),
                new Date(121,1,28)));
        list.add(  new FlightDTO( "BOBA-6567",
                 "Bogotá",
                "Buenos Aires",
                "Economy",
                 39860.0,
                new Date(121,02,1) ,
                new Date(121,2,14)));


        return list;
    }

}
