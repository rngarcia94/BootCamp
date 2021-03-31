package starwars.star_wars.servicies;

import starwars.star_wars.dto.PersonajesDTO;

import java.util.List;

public interface PersonajeService {

    List<PersonajesDTO> getPersonaje(String nombre);

}
