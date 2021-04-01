package com.company;

public class Circulo extends FiguraGeometrica{

    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public double area() {
        return Math.PI*Math.pow(this.radio,2);
    }
}
