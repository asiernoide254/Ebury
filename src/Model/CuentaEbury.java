package Model;

import java.sql.Date;

public abstract class CuentaEbury {
    private String id;
    private String numeroCuenta;
    private Enum<TipoEstadoCuenta> estadoCuenta;
    private Date fechaApertura;
    private Date fechaCierre;

    /**No sabemos lo que es entidadEbury, pero como no se ha usado
    esta enumeracion en el modelo pues la ponemos, porque si*/
    private Enum<ClasificacionCuenta> entidadEbury;
    private PersonaRelacionada[] autorizados; //No se especifica multiplicidad en el modelo
    private Transaccion[] transacciones;
}
