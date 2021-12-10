package Model;

import java.sql.Date;

public abstract class Cliente {
    private String id;
    private String numeroIdentificacion;
    private Enum<TipoEstadoCliente> estado;
    private Date fechaInicio;

    private CuentaEbury[] cuentas;
    private Direccion[] direcciones;
    private PersonaRelacionada[] relacionadas;
}
