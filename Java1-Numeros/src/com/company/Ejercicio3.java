package com.company;
import java.util.Scanner;
public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese numero N:");
        int n = scanner.nextInt();
        boolean f = true;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                f = false;
                break;
            }
        }
        if (f){
            System.out.println(n + " Es primo");
        }
        else {
            System.out.println(n + " No es Primo");
        }
        scanner.close();
    }
}