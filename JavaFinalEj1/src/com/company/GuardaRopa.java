package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuardaRopa {
    private int contador;
    Map<Integer,Prenda> dicc = new HashMap<Integer,Prenda>();

    public Integer guardarPrendas(List<Prenda> listaDePrenda){
        //int[] arr = new int[listaDePrenda.size()];
        int salida = 0;
        for(int i = 0; i<listaDePrenda.size(); i++){
            dicc.put(i,listaDePrenda.get(i));
            contador++;
            salida = i;
        }
        return salida;
    }

    public void mostrarPrendas(){
        dicc.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public List<Prenda> devolverPrendas(Integer numero){
        List<Prenda> res = new ArrayList<Prenda>();
        res.add( dicc.get(numero));
        dicc.remove(1);
        return res;
    }

}
