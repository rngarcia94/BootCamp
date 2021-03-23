package com.company;

public class Rectangulo extends FiguraGeometrica{

    private double base;
    private double altura;

    public double getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    @Override
    public double area() {
        return this.base * this.altura;
    }
}
