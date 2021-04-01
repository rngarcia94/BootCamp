package com.company;

import java.util.Comparator;

public class BubbleSortImple<T> implements Sorter<T>{

    @Override
    public void sort(T[] arr, Comparator<T> c) {
        boolean sorted = false;
        while (!sorted){
            sorted = true;
            for (int i = 0; i < arr.length -1; i++){
                if (c.compare(arr[i],arr[i+1]) > 0){
                    T temp = arr[i];
                    arr[i] = arr[1+i];
                    arr[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }
}
