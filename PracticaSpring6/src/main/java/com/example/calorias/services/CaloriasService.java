package com.example.calorias.services;

import com.example.calorias.dto.IngredienteDTO;
import com.example.calorias.dto.PlatoDTO;

import java.util.List;

public interface CaloriasService {

    double obtenerCaloriasTotales(PlatoDTO plato);
    List<IngredienteDTO> obtenerCaloriasIngredientes(PlatoDTO plato);
    IngredienteDTO ingredienteConMasCalorias(PlatoDTO plato);
}
