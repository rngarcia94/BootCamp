public class Main {

    public static void main(String[] args) {
        boolean f = false;
        Fecha fecha = new Fecha(12,3,2021);
        f = Fecha.checkDate(fecha);

        System.out.println(f);
        System.out.println(Fecha.addDate(fecha,36));
    }
}
