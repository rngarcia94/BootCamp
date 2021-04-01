package com.company;

public class Invitados {
    private String nombre;

    public void saludar(){
        System.out.println("Gracias");
    }

    public Invitados(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
