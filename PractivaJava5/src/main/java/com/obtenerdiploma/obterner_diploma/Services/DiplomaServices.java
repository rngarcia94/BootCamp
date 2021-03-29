package com.obtenerdiploma.obterner_diploma.Services;

import com.obtenerdiploma.obterner_diploma.Entities.Alumno;
import com.obtenerdiploma.obterner_diploma.Entities.Diploma;
import com.obtenerdiploma.obterner_diploma.Entities.Materias;

public class DiplomaServices {

    public static Diploma obtenerDiploma(Alumno alumno){
        double promedio = 0;
        String mensaje = "";
        for (Materias m : alumno.getListaMaterias()){
            promedio += m.getNota();
        }
        promedio = promedio/ alumno.getListaMaterias().size();
        if(promedio>=9){
            mensaje = "Felicitaciones";
        }
        return new Diploma(mensaje,promedio,alumno);
    }
}
