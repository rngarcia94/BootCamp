import java.util.Arrays;

public  class Main {

    public static void main(String[] args) {

        Persona lisi = new Persona("lisi", 123);
        Persona tomi = new Persona("tomi", 456);
        Persona rocha = new Persona("rocha", 789);

        Celular moto = new Celular(11,"aaa");
        Celular sam = new Celular(42,"bbb");
        Celular lg = new Celular(23,"ccc");

        Persona[] arr = {lisi, tomi, rocha};
        Celular[] arb = {moto,sam,lg};
        SortUtil.ordenar(arb);
        SortUtil.ordenar(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arb));
    }
}
