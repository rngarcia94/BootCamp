package com.calcularedad.calcular_edad.Services;

import com.calcularedad.calcular_edad.DTO.EdadDTO;

import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

public class EdadService {

    public static int obtenerEdad(EdadDTO edad){
        LocalDate date = LocalDate.of(edad.getYear(),edad.getMes(),edad.getDia());
        Period period = Period.between(date, LocalDate.now());
        return period.getYears();

    }
}
