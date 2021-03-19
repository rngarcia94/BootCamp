package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un numero:");
        int n = scanner.nextInt();
        int j = 0;
        int i = 0;
        while(j<n){
            if (i%2 == 0){
                System.out.println(i);
                j++;
            }
            i++;
        }
        scanner.close();
    }
}
