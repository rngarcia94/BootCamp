package com.metroscuadraos.metrocuadrados.Services;

import com.metroscuadraos.metrocuadrados.DTO.CasaDto;
import com.metroscuadraos.metrocuadrados.DTO.HabitacionesDTO;

import java.util.ArrayList;
import java.util.List;

public class CasaService {

    public static double metrosCuadradosPorHabitacion(HabitacionesDTO habitacion){
            return habitacion.getAncho() * habitacion.getLargo();

    }

    public static double metrosCuadrados(CasaDto casa){
        double resultado = 0;
        for (int i = 0; i < casa.getHabitaciones().size(); i++) {
            resultado += metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i));
        }
        return resultado;
    }

    public static double valorDeLaCasa(CasaDto casa){
        return 800 * metrosCuadrados(casa);
    }

    public static HabitacionesDTO habitacionMasGrande(CasaDto casa){
        double mayor = 0;
        int posMayor = 0;
        for (int i = 0; i < casa.getHabitaciones().size(); i++) {
            if(metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i)) > mayor){
                mayor = metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i));
                posMayor = i;
            }
        }
        return casa.getHabitaciones().get(posMayor);
    }

    public static List<Double> listaDeMetrosCuadradosPorHabitacion(CasaDto casa){
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < casa.getHabitaciones().size(); i++) {
            list.add(metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i)));

        }
        return list;
    }


}
