package com.example.calorias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private double caloriasTotales;
    private List<IngredienteDTO> caloriasIngredientes;
    private IngredienteDTO ingredienteConMasCalorias;
}
