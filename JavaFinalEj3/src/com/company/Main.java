package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Evento evento = new Evento();

        // Creo invitado standard
        Invitados inv1 = new Invitados("Jorge");
        Invitados inv2 = new Invitados("Manuel");

        // Creo invitado meli
        Invitados inv3 = new InvitadoMeLi("Juan");
        Invitados inv4 = new InvitadoMeLi("Tomás");

        // Agrego invitados
        evento.addInvitado(inv1);
        evento.addInvitado(inv2);
        evento.addInvitado(inv3);
        evento.addInvitado(inv4);

        // Creamos 6 fuegos
        FuegoArtificalIndividual fuego1 = new FuegoArtificalIndividual("pum");
        FuegoArtificalIndividual fuego2 = new FuegoArtificalIndividual("pa");
        FuegoArtificalIndividual fuego3 = new FuegoArtificalIndividual("rartatat");
        FuegoArtificalIndividual fuego4 = new FuegoArtificalIndividual("fiiuuuuu");
        FuegoArtificalIndividual fuego5 = new FuegoArtificalIndividual("shhh shhh pa");
        FuegoArtificalIndividual fuego6 = new FuegoArtificalIndividual("BOOOM");
        // Agregamos 3 individuales
        evento.addFuego(fuego1);
        evento.addFuego(fuego2);
        evento.addFuego(fuego3);
        // Y los otros tres en un pack
        FuegoArtificualPack pack1 = new FuegoArtificualPack();
        pack1.addFuego(fuego4);
        pack1.addFuego(fuego5);
        pack1.addFuego(fuego6);
        evento.addFuego(pack1);

        //Soplamos las velas
        evento.soplarVelas();


    }
}
