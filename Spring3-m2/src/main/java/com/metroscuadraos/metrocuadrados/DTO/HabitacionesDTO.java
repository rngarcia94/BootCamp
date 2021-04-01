package com.metroscuadraos.metrocuadrados.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionesDTO {
    private String nombre;
    private double ancho;
    private double largo;
}
