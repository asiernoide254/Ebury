package Model;

import java.sql.Date;

/**Extiende de Persona y Cliente (mirar como implementar)*/
public class PersonaRelacionada extends Persona {
    private Enum<TipoClienteRelacionado> tipoClienteRelacionado;
    private Date fechaFin;

    private CuentaEbury[] cuentas;
}
