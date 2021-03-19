package com.company;
import java.util.Scanner;
public class Ejercicio2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese numero N:");
        int n = scanner.nextInt();

        System.out.println("Ingrese numero M");
        int m = scanner.nextInt();
        int j = 0;
        int i = 0;
        while(j<n){
            if (i%m == 0){
                System.out.println(i);
                j++;
            }
            i++;
        }
        scanner.close();
    }
}