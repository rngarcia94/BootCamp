package com.metroscuadraos.metrocuadrados.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private double metrosCuadrados;
    private double valor;
    private HabitacionesDTO habitacionesMasGrade;
    private List<Double> MetrosCuadradosPorHabitacion;

}
