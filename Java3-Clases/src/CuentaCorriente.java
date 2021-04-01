public class CuentaCorriente {

   private double saldo;

   public CuentaCorriente(){
       this.saldo = 0;
   }

   public CuentaCorriente(String titular){
       this.saldo = saldo;
   }

    public CuentaCorriente(double saldo) {
        this.saldo = saldo;
    }

    public CuentaCorriente(CuentaCorriente cc){
       this.saldo = cc.saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void ingreso(double ingreso){
       this.saldo = saldo;
    }

    public boolean egreso(double egreso) {
        if (egreso > this.saldo) {
            return false;
        }
        else {
            return true;
        }
    }
    public void reintegro(){}
    public void transferencia(){}

}
