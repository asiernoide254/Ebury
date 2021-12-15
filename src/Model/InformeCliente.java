package Model;

public class InformeCliente {
    String iban;
    String apellido;
    String nombre;
    String calle;
    String ciudad;
    String cp;
    String pais;
    String nif;
    String fechaNacimiento_fechaRegistro;
    String fechaApertura;
    String fechaCierre;
    String activa;
    String personasRelacionadas;

    public InformeCliente(String iban, String apellido, String nombre, String calle, String ciudad, String cp, String pais, String nif, String fechaNacimiento_fechaRegistro, String fechaApertura, String fechaCierre, String activa, String personasRelacionadas) {
        this.iban = iban;
        this.apellido = apellido;
        this.nombre = nombre;
        this.calle = calle;
        this.ciudad = ciudad;
        this.cp = cp;
        this.pais = pais;
        this.nif = nif;
        this.fechaNacimiento_fechaRegistro = fechaNacimiento_fechaRegistro;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.activa = activa;
        this.personasRelacionadas = personasRelacionadas;
    }
}
