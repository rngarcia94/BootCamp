package com.company;

public class FuegoArtificalIndividual implements FuegoArtificial{
    private String sonido;

    @Override
    public void explotar() {
        System.out.println(sonido);
    }

    public FuegoArtificalIndividual(String sonido) {
        this.sonido = sonido;
    }


}
