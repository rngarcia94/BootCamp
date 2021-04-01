package com.company;

public class InvitadoMeLi extends Invitados{

    public InvitadoMeLi(String nombre) {
        super(nombre);
    }

    @Override
    public void saludar() {
        System.out.println("Viva la Chiqui!!");
    }
}
