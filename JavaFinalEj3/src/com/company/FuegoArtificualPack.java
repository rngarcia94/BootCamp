package com.company;

import java.util.ArrayList;

public class FuegoArtificualPack implements FuegoArtificial{

    private ArrayList<FuegoArtificalIndividual> fuegos;

    public FuegoArtificualPack(){
        this.fuegos = new ArrayList<FuegoArtificalIndividual>();
    }

    public void addFuego(FuegoArtificalIndividual fuego){
        this.fuegos.add(fuego);
    }

    @Override
    public void explotar() {
        for (FuegoArtificalIndividual fuego : fuegos) {
            fuego.explotar();
        }
    }
}
