package challenge.challenge.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelTestHelper {

    public List<HotelDTO> getAllTestHotels(){
        List<HotelDTO> list = new ArrayList<>();
        list.add(new HotelDTO(    "CH-0002",
                 "Cataratas Hotel",
                 "Puerto Iguazú",
                "Doble",
                 6300.0,
                 new Date(121,1,10),
                 new Date(121,2,20),
                false));
        list.add(new HotelDTO(   "CH-0003",
                "Cataratas Hotel 2",
                "Puerto Iguazú",
                 "Triple",
                 8200.0,
                 new Date(121,1,10),
                 new Date(121 ,2,23),
                false));
        list.add(new HotelDTO( "HB-0001",
                "Hotel Bristol",
                 "Buenos Aires",
                 "Single",
                 5435.0,
                new Date(121,1,10),
                new Date(121,2,19),
                 false));
        list.add(new HotelDTO( "BH-0002",
                "Hotel Bristol 2",
                 "Buenos Aires",
                 "Doble",
                 7200.0,
                 new Date(121,1,12),
                 new Date(121,3,17),
                 false));


        return list;
    }
}
