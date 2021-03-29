package com.obtenerdiploma.obterner_diploma.Controllers;


import com.obtenerdiploma.obterner_diploma.Entities.Alumno;
import com.obtenerdiploma.obterner_diploma.Entities.Diploma;
import com.obtenerdiploma.obterner_diploma.Services.DiplomaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiplomaController {

    @PostMapping("/diploma")
    public ResponseEntity<Diploma> obtenerDiploma(@RequestBody Alumno alumno){
        Diploma diploma = DiplomaServices.obtenerDiploma(alumno);

        return new ResponseEntity<>(diploma, HttpStatus.OK);

    }
}
