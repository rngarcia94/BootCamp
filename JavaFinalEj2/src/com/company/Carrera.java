package com.company;

import java.util.LinkedList;
import java.util.List;

public class Carrera {
    public Carrera(double distancia, double premioEnDolares, String nombre, int cantidadDeVehiculosPermitidos) {
        this.distancia = distancia;
        this.premioEnDolares = premioEnDolares;
        this.nombre = nombre;
        this.cantidadDeVehiculosPermitidos = cantidadDeVehiculosPermitidos;
    }

    public SoccoristaAuto soccoristaAuto;
    public SocorristaMoto socorristaMoto;
    private double distancia;
    private double premioEnDolares;
    private String nombre;
    private int cantidadDeVehiculosPermitidos;
    List<Vehiculo> listaDeVehiculos = new LinkedList<Vehiculo>();

    public void darAltaAuto(double velocidad, double aceleracion, double anguloDeGiro, String patente){
        if (listaDeVehiculos.size() < cantidadDeVehiculosPermitidos){
             Autos aa = new Autos(velocidad,aceleracion,anguloDeGiro,patente);
            listaDeVehiculos.add(aa);
        }
    }

    public void darAltaMoto(double velocidad, double aceleracion, double anguloDeGiro, String patente){
        if (listaDeVehiculos.size() < cantidadDeVehiculosPermitidos){
            Motos aa = new Motos(velocidad,aceleracion,anguloDeGiro,patente);
            listaDeVehiculos.add(aa);
        }
    }

    public void eliminarVehiculoConPatente(String unaPatente){
        for(int i = 0; i < listaDeVehiculos.size(); i++){
            if(listaDeVehiculos.get(i).getPatente().equals(unaPatente)){
                listaDeVehiculos.remove(i);
            }
        }
    }

    public void eliminarVehiculo(Vehiculo vehiculo){
        eliminarVehiculoConPatente(vehiculo.getPatente());
    }

    public void mostrarParticipantes(){
        for (int i = 0; i < listaDeVehiculos.size(); i++) {
            System.out.println(listaDeVehiculos.get(i).toString());
        }
    }

    /*
    public Vehiculo calcularGanadorCarrera(){
        Vehiculo mejor = listaDeVehiculos.get(0);
        for (int i = 0; i < listaDeVehiculos.size(); i++) {
            if (mejor.calcularGanador() > listaDeVehiculos.get(i).calcularGanador()) {
                mejor = listaDeVehiculos.get(i);
            }
        }
        return mejor;
    }*/
    public Vehiculo calcularGanadorCarrera() {
        Vehiculo mejor = listaDeVehiculos.get(0);
        for (Vehiculo listaDeVehiculo : listaDeVehiculos) {
            if (mejor.calcularGanador() > listaDeVehiculo.calcularGanador()) {
                mejor = listaDeVehiculo;
            }
        }
        return mejor;
    }

    public void socorrerAuto(String patente){
        for (Vehiculo listaDeVehiculo : listaDeVehiculos) {
            if (listaDeVehiculo.getPatente().equals(patente) && listaDeVehiculo.getClass().toString().equals("Autos")) {
                soccoristaAuto.socorrer((Autos) listaDeVehiculo);
            }
        }
    }

    public void socorrerMoto(String patente){
        for (Vehiculo listaDeVehiculo : listaDeVehiculos) {
            if (listaDeVehiculo.getPatente().equals(patente) && listaDeVehiculo.getClass().toString().equals("Motos")) {
                socorristaMoto.socorrer((Motos) listaDeVehiculo);
            }
        }
    }
}
