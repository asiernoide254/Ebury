package Model;

public class Pago extends Transaccion {
    private String moneda;
    private double cantidad;

    private Beneficiario beneficiario;
    /**Puede tener 1 o 0 cambios de divisa*/
    private CambioDivisa cambioDivisa;
}
