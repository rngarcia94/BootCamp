public class Contador {
    private int valor;

    public Contador(int valor){
        this.valor = valor;
    }

    public Contador(){
        this.valor = 0;
    }

    public Contador(Contador cc){
        this.valor = cc.getValor();
    }

    public int aumentar(int aumento) {
        return this.valor = valor + aumento;
    }

    public int disminuir(int disminucion){
        return this.valor = valor + disminucion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
