package com.company;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese numero N:");
        int n = scanner.nextInt();
        System.out.println("Ingrese numero M:");
        int m = scanner.nextInt();
        System.out.println("Ingrese numero D:");
        int d = scanner.nextInt();
        int j = 0;
        int r = 0;
        while(j<n){
            j = j + cumple(r,d,m);
            r++;

        }
        scanner.close();
    }

    public static int cumple(int a, int b, int c){
        String str = Integer.toString(a);
        int f = 0;
        int p = 0;
        for (int i = 0; i < str.length(); i++){
            char k = str.charAt(i);
            if (String.valueOf(k).equals(String.valueOf(b))){
                p++;
            }
        }
        if (p == c){
            f = 1;
            System.out.println(a);
        }
        return f;
    }
}
