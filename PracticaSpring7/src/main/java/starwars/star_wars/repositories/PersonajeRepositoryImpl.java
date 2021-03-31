package starwars.star_wars.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import starwars.star_wars.dto.PersonajesDTO;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class PersonajeRepositoryImpl implements PersonajeRepository{

    @Override
    public List<PersonajesDTO> getPersonaje(String name) {
        List<PersonajesDTO> personajesDTOs = null;
        personajesDTOs = loadDataBase();
        List<PersonajesDTO> item = null;

        if (personajesDTOs != null) {
                 item = personajesDTOs.stream()
                        .filter(personajesDTO -> personajesDTO.getName().contains(name))
                        .collect(Collectors.toList());
                return item;
        }
        return item;
    }

    private List<PersonajesDTO> loadDataBase() {
        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:starwars.json");
        }catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PersonajesDTO>> typeRef = new TypeReference<List<PersonajesDTO>>() {};
        List<PersonajesDTO> personajesDTOs = null;

        try {
            personajesDTOs = objectMapper.readValue(file, typeRef);
        }catch (Exception e){
            e.printStackTrace();
        }

        return personajesDTOs;
    }
}
