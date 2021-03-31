package starwars.star_wars.repositories;

import starwars.star_wars.dto.PersonajesDTO;

import java.util.List;

public interface PersonajeRepository {

    List<PersonajesDTO> getPersonaje (String nombre);
}
