package Model;

import java.sql.Date;

/**Extiende de Persona y Cliente (mirar como implementar)*/
public class PersonaRelacionada extends Cliente {
    private Enum<TipoClienteRelacionado> tipoClienteRelacionado;
    private Date fechaFin;

    private Persona persona;
    private CuentaEbury[] cuentas;
}
