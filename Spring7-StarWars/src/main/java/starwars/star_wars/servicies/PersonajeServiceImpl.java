package starwars.star_wars.servicies;

import org.springframework.stereotype.Service;
import starwars.star_wars.dto.PersonajesDTO;
import starwars.star_wars.repositories.PersonajeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonajeServiceImpl implements PersonajeService{

    private PersonajeRepository personajeRepository;

    public PersonajeServiceImpl(PersonajeRepository personajeRepository){this.personajeRepository = personajeRepository;}

    @Override
    public List<PersonajesDTO> getPersonaje(String nombre) {
        return personajeRepository.getPersonaje(nombre);
    }

}
