package starwars.star_wars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import starwars.star_wars.servicies.PersonajeServiceImpl;

@RestController
@RequestMapping("starwars")
public class StarWarsController {

    @Autowired
    private PersonajeServiceImpl personajeService;

    @GetMapping("/")
    public ResponseEntity getPersonaje(@RequestParam String nombre ){
        return new ResponseEntity(personajeService.getPersonaje(nombre),HttpStatus.OK);
    }
}
