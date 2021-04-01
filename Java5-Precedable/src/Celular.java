public class Celular implements Precedable<Celular>{

    private int numero;
    private String titula;

    public Celular(int numero, String titula) {
        this.numero = numero;
        this.titula = titula;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    @Override
    public int precedeA(Celular celular) {
        if (this.numero < celular.numero){
            return -1;
        }
        else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Celular{" +
                "numero=" + numero +
                ", titula='" + titula + '\'' +
                '}';
    }
}
