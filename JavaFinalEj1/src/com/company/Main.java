package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {
	    Prenda a = new Prenda("aa","11");
	    Prenda b = new Prenda("bb","22");

        List<Prenda> ropa = new LinkedList<>();
        ropa.add(a);
        ropa.add(b);

        GuardaRopa ropero = new GuardaRopa();
        System.out.println("Guardo");
        int r = ropero.guardarPrendas(ropa);
        System.out.println(r);
        ropero.mostrarPrendas();
        System.out.println("Saco");
        System.out.println(ropero.devolverPrendas(1).toString());
        ropero.mostrarPrendas();
    }
}
