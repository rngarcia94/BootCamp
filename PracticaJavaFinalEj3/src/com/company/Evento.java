package com.company;

import java.util.ArrayList;

public class Evento {

    private ArrayList<Invitados> invitados;
    private ArrayList<FuegoArtificial> fuegoArtificials;


    public Evento(){
        this.fuegoArtificials = new ArrayList<FuegoArtificial>();
        this.invitados = new ArrayList<Invitados>();
    }

    public void addInvitado(Invitados invitado){
        this.invitados.add(invitado);
    }

    public void addFuego(FuegoArtificial fuego){
        this.fuegoArtificials.add(fuego);
    }

    public void soplarVelas(){
        for (Invitados invitados : invitados) {
            invitados.saludar();
        }
        for (FuegoArtificial fuegos: fuegoArtificials) {
            fuegos.explotar();
        }
    }

}
