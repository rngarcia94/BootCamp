package com.metroscuadraos.metrocuadrados.Controller;


import com.metroscuadraos.metrocuadrados.DTO.CasaDto;
import com.metroscuadraos.metrocuadrados.DTO.ResponseDTO;
import com.metroscuadraos.metrocuadrados.Services.CasaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CasaController {

    @PostMapping("/casa")
    public ResponseEntity metrosCuadrados(@RequestBody CasaDto casa){
        ResponseDTO response = new ResponseDTO();
        response.setMetrosCuadrados(CasaService.metrosCuadrados(casa));
        response.setValor(CasaService.valorDeLaCasa(casa));
        response.setHabitacionesMasGrade(CasaService.habitacionMasGrande(casa));
        response.setMetrosCuadradosPorHabitacion(CasaService.listaDeMetrosCuadradosPorHabitacion(casa));

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
