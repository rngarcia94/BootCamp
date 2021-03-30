package com.example.calorias.repositories;

import com.example.calorias.dto.CaloriaDTO;

public interface CaloriaRepository {

    CaloriaDTO findCaloriesByName(String name);
}
