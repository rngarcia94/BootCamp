public class SortUtil {

    public static<T> void ordenar(Precedable<T>[] arr){
        boolean sorted = false;
        Precedable<T> temp;
        while (!sorted){
            sorted = true;
            for (int i = 0; i < arr.length -1; i++){
                if (arr[i].precedeA((T)arr[i+1]) > 0){
                    temp = arr[i];
                    arr[i] = arr[1+i];
                    arr[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }
}
