package com.calcularedad.calcular_edad.Controllers;

import com.calcularedad.calcular_edad.DTO.EdadDTO;
import com.calcularedad.calcular_edad.Services.EdadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EdadController {

    @GetMapping("/{day}/{month}/{year}")
    public ResponseEntity obetenerEdad(@PathVariable int day,@PathVariable int month,@PathVariable int year){
        EdadDTO edad = new EdadDTO(day,month,year);
        return new ResponseEntity(EdadService.obtenerEdad(edad), HttpStatus.OK);
    }

    @GetMapping("/edad")
    public ResponseEntity obetenerEdadParam(@RequestParam("day") int day, @RequestParam("month") int month, @RequestParam("year") int year){
        EdadDTO edad = new EdadDTO(day,month,year);
        return new ResponseEntity(EdadService.obtenerEdad(edad), HttpStatus.OK);
    }

}
