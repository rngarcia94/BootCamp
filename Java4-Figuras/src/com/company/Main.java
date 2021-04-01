package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String pass = "aaaaaaaa2";
        Boolean f = false;
        f = PasswordIntermedia.checkPassword(pass);
        System.out.println(f);
        Triangulo t = new Triangulo(5,5);
        Rectangulo r = new Rectangulo(4,4);
        Circulo c = new Circulo(5);
        FiguraGeometrica[] arr = new FiguraGeometrica[3];
        arr[0] = t;
        arr[1] = r;
        arr[2] = c;
        double s = Utility.areaPromedio(arr);
        System.out.println(s);

    }
}
