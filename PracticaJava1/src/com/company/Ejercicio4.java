package com.company;

import java.util.Scanner;

public class Ejercicio4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese numero N:");
        int n = scanner.nextInt();

        int count = 0;
        int num = 2;
        while(count != n){
            boolean prime = true;
            for (int i = 2;i <= Math.sqrt(num); i++){
                if (num % i == 0){
                    prime = false;
                    break;
                }
            }
            if(prime){
                count++;
                System.out.println(num);
            }
            num++;
        }

        scanner.close();
    }
}
