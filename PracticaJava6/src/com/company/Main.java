package com.company;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
	// write your code here
        Time tiempo = new Time();
        Object[] arr = {12,312,43,2,54,12,654,434,123};
        Integer[] arr5 = new Integer[10000];
        for (int x=0; x < arr5.length; x++) {
            arr5[x] = (int) (Math.random()*1000)+1;
        }
        Object[] ass = {"papa", "camote", "lechuga", "huevos","remolacha"};
        java.util.Comparator<Integer> ci = Integer::compareTo;
        Comparator<String> cs = String::compareTo;

        Sorter s = (Sorter) MiFactory.getInstance("/Users/rodgarcia/Desktop/Bootcamp/PracticaJava6/MiFactory.properties");
        tiempo.start();
        s.sort(arr5,ci);
        tiempo.stop();
        System.out.println(tiempo.elapsedTime());
        s.sort(ass,cs);
        System.out.println(Arrays.toString(arr5));
        System.out.println(Arrays.toString(ass));

    }
}
