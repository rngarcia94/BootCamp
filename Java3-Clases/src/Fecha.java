import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {

    private int day;
    private int month;
    private int year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Fecha(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Fecha(){
        this.day = 21;
        this.month = 1;
        this.year = 1977;
    }

    public  Fecha(Fecha f){
        this.day = f.day;
        this.month = f.month;
        this.year = f.year;
    }

    @Override
    public String toString(){
        return  "Fecha: " + this.day + "/" + this.month + "/" + this.year;
    }

    public static boolean checkDate(Fecha f){
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(f.year, f.month,f.day);
        gc.setLenient(false);
        try{
            gc.getTime();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static Fecha addDate(Fecha f, int i){
        GregorianCalendar gg = new GregorianCalendar();
        gg.set(f.year,f.month,f.day);
        if(Fecha.checkDate(f)) {
            gg.add(Calendar.DATE, i);
            f.year = gg.get(Calendar.YEAR);
            f.month = gg.get(Calendar.MONTH);
            f.day = gg.get(Calendar.DATE);
            return f;
        }
        return null;
    }
}




