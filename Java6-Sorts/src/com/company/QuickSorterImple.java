package com.company;

import javax.management.ObjectName;
import java.util.Comparator;

public class QuickSorterImple<T> implements Sorter<T>{

    @Override
    public void sort(T[] arr, Comparator<T> c) {
        quickSort(arr,0, arr.length-1, c);

    }

    private void quickSort(T[] a, int izq, int der, Comparator<T> c) {
        T pivote = a[izq];
        int i = izq;
        int j = der;
        T aux;

        while (i<j){
            while (c.compare(a[i],pivote) <= 0 && i < j) i++;
            while (c.compare(a[j],pivote) > 0) j--;

            if(i<j){
                aux = a[i];
                a[i] = a[j];
                a[j] = aux;
            }
        }

        a[izq] = a[j];
        a[j] = pivote;

        if (izq < j-1){
            quickSort(a,izq,j-1,c);
        }
        if(j+1 < der){
            quickSort(a,j+1,der,c);
        }
    }
}
