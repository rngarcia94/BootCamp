package com.PracticaSpring2.PracticaSpring2.Controllers;

import com.PracticaSpring2.PracticaSpring2.Entities.CodigoMorse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/codigo")
public class CodigoMorseController {

    @GetMapping("/convertir/{codigo}")
    public CodigoMorse convetir(@PathVariable() String codigo){
        CodigoMorse codigoMorse = new CodigoMorse(codigo);
        codigoMorse.convertirCodigo();
        return codigoMorse;
    }

}
