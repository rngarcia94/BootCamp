package com.metroscuadraos.metrocuadrados.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasaDto {

    private String nombre;
    private String direccion;
    private List<HabitacionesDTO> habitaciones;
}
