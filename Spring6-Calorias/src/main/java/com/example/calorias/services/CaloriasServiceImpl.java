package com.example.calorias.services;

import com.example.calorias.dto.IngredienteDTO;
import com.example.calorias.dto.PlatoDTO;
import com.example.calorias.dto.ResponseDTO;
import com.example.calorias.repositories.CaloriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaloriasServiceImpl implements CaloriasService {

    private CaloriaRepository caloriaRepository;
    private static List<IngredienteDTO> ingredientes;

    public CaloriasServiceImpl(CaloriaRepository caloriaRepository){
        this.caloriaRepository = caloriaRepository;
    }

    @Override
    public double obtenerCaloriasTotales(PlatoDTO plato) {
        double resultado = 0;
        ingredientes = obtenerCaloriasIngredientes(plato);
        for(IngredienteDTO ingrediente : ingredientes){
            resultado += ingrediente.getCalorias();
        }
        return resultado;
    }

    @Override
    public List<IngredienteDTO> obtenerCaloriasIngredientes(PlatoDTO plato) {
        List<IngredienteDTO> ingredientes = new ArrayList<>();

        for(IngredienteDTO ingrediente : plato.getIngredientes()) {
            ingrediente.setCalorias(caloriaRepository.findCaloriesByName(ingrediente.getNombre()).getCalories() * ingrediente.getPeso() / 100);
            ingredientes.add(ingrediente);
        }
        return ingredientes;
    }

    @Override
    public IngredienteDTO ingredienteConMasCalorias(PlatoDTO plato) {
        IngredienteDTO ingredienteAux = new IngredienteDTO();
        double max = 0;
        for(IngredienteDTO ingrediente : ingredientes){
            if(ingrediente.getCalorias() > max){
                max = ingrediente.getCalorias();
                ingredienteAux = ingrediente;
            }
        }
        return ingredienteAux;
    }

    public ResponseDTO getResponse(PlatoDTO plato){
        return new ResponseDTO(obtenerCaloriasTotales(plato), this.ingredientes, ingredienteConMasCalorias(plato));
    }
}