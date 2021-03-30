package com.example.calorias.controllers;

import com.example.calorias.dto.PlatoDTO;
import com.example.calorias.services.CaloriasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calorias")
public class CaloriasController{

    @Autowired
    private CaloriasServiceImpl caloriasService;

    @PostMapping("/")
    public ResponseEntity obtenerCalorias(@RequestBody PlatoDTO plato){ return new ResponseEntity(caloriasService.getResponse(plato), HttpStatus.OK); }
}
