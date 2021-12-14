package Controller;

import Model.*;
import View.Holanda.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.sql.SQLException;

public class ControllerHolanda {
    public void onFiltrarPorCliente(JFrame frame) {
        FiltrarPorCliente fpc = new FiltrarPorCliente(frame);
        frame.setContentPane(fpc.getPanel1());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void onFiltrarPorCuenta(JFrame frame) {
        FiltrarPorCuentaBancaria fpcb = new FiltrarPorCuentaBancaria(frame);
        frame.setContentPane(fpcb.getPanel1());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void onComprobarConexion() {
        try {
            new BD();
            ExitoHolanda dialog = new ExitoHolanda();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } catch (SQLException e) {
            ErrorHolanda dialog = new ErrorHolanda();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }

    public void onVolver(JFrame frame) {
        // add your code here
        frame.dispose();

        PrincipalHolanda ph = new PrincipalHolanda("Ebury");
        ph.pack();
        ph.setLocationRelativeTo(null);
        ph.setVisible(true);

    }

    public void onBuscarCuentaBancaria(String str, String text, JTable tabla) {
        final String IND = "SELECT C.id, SD.cuentaReferencia 'IBAN', concat(P.apellido, ' ', COALESCE(P.segundoApellido, '')) 'apellidos', " +
                "concat(P.nombre, ' ', COALESCE(P.segundoNombre, '')) 'nombre', D.calle, D.ciudad, D.codigoPostal, D.pais, C.numeroIdentificacion, P.fechaNacimiento, CE.fechaApertura, CE.fechaCierre, CE.estadoCuenta " +
                "FROM CuentaEbury CE, Cliente C, Individual I, Persona P, Direccion D, (SELECT * FROM Segregada UNION SELECT * FROM Dedicada) SD, CuentaBanco CB " +
                "WHERE " +
                "CE.id = SD.id AND C.id = CE.propietario AND D.cliente = C.id AND D.valida = 1 AND " +
                "C.id = I.cliente AND I.persona = P.id AND SD.cuentaReferencia = CB.ibanCuenta AND CB.pais = 'Holanda' AND (YEAR(CURDATE()) - YEAR(CE.fechaCierre) <= 3 OR CE.fechaCierre IS NULL) AND CB.ibanCuenta LIKE '%" + text + "%'";
        final String EMP = "SELECT C.id, SD.cuentaReferencia 'IBAN', null, E.nombre, D.calle, D.ciudad, D.codigoPostal, D.pais, C.numeroIdentificacion, E.fechaRegistro, CE.fechaApertura, CE.fechaCierre, CE.estadoCuenta " +
                "FROM CuentaEbury CE, Cliente C, Empresa E, Direccion D, (SELECT * FROM Segregada UNION SELECT * FROM Dedicada) SD, CuentaBanco CB " +
                "WHERE " +
                "CE.id = SD.id AND C.id = CE.propietario AND D.cliente = C.id AND D.valida = 1 AND " +
                "C.id = E.cliente AND SD.cuentaReferencia = CB.ibanCuenta AND CB.pais = 'Holanda' AND (YEAR(CURDATE()) - YEAR(CE.fechaCierre) <= 3 OR CE.fechaCierre IS NULL) AND CB.ibanCuenta LIKE '%" + text + "%'";

        try {
            System.out.println("BD cargada");
            BD myBD = new BD();
            List<Object[]> resultado;

            switch (str) {
                case "Todas" -> {
                    System.out.println("Caso todas");
                    resultado = myBD.Select(IND + ";");
                    resultado.addAll(myBD.Select(EMP + ";"));
                }
                case "Cuentas activas" -> {
                    System.out.println("Caso activas");
                    resultado = myBD.Select(IND + " AND CE.estadoCuenta = 'Activa';");
                    resultado.addAll(myBD.Select(EMP + " AND CE.estadoCuenta = 'Activa';"));
                }
                default -> {
                    System.out.println("Caso cerrada/bloqueada");
                    resultado = myBD.Select(IND + " AND (CE.estadoCuenta = 'Cerrada' OR CE.estadoCuenta = 'Bloqueada');");
                    resultado.addAll(myBD.Select(EMP + " AND (CE.estadoCuenta = 'Cerrada' OR CE.estadoCuenta = 'Bloqueada');"));
                }
            }

            String[] nombreColumnas = {"IBAN", "Apellido", "Nombre", "Calle", "Ciudad", "CP", "País", "NIF", "Fecha de Nacimiento/Registro", "Fecha de Apertura", "Fecha de cierre", "Activa"};
            rellenarTabla(nombreColumnas, tabla, resultado);

        } catch (SQLException e) {
            ErrorHolanda dialog = new ErrorHolanda();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }

    private void rellenarTabla(String[] nombreColumnas, JTable tabla, List<Object[]> resultado) {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        dtm.setColumnIdentifiers(nombreColumnas);
        tabla.setModel(dtm);

        for(Object[] tupla : resultado) {
            //Borramos la columna id de tupla
            Object[] tuplaDefinitiva = Arrays.copyOfRange(tupla, 1, tupla.length);
            System.out.println(Arrays.toString(tuplaDefinitiva));
            for (int i = 0; i < tuplaDefinitiva.length; i++) {
                if (tuplaDefinitiva[i] == null) {
                    tuplaDefinitiva[i] = "No Existente";
                }
            }
            dtm.addRow(tuplaDefinitiva);
        }
    }

    public void onBuscarCliente(String str, String text, JTable table) {
        final String IND = "SELECT C.id, SD.cuentaReferencia 'IBAN', concat(P.apellido, ' ', COALESCE(P.segundoApellido, '')) 'apellidos', concat(P.nombre, ' ', COALESCE(P.segundoNombre, '')) 'nombre', D.calle, D.ciudad, D.codigoPostal, D.pais, C.numeroIdentificacion, P.fechaNacimiento, CE.fechaApertura, CE.fechaCierre, CE.estadoCuenta " +
                "FROM CuentaEbury CE, Cliente C, Individual I, Persona P, Direccion D, (SELECT * FROM Segregada UNION SELECT * FROM Dedicada) SD, CuentaBanco CB " +
                "WHERE " +
                "CE.id = SD.id AND C.id = CE.propietario AND D.cliente = C.id AND D.valida = 1 AND " +
                "C.id = I.cliente AND I.persona = P.id AND SD.cuentaReferencia = CB.ibanCuenta AND CB.pais = 'Holanda' AND (YEAR(CURDATE()) - YEAR(CE.fechaCierre) <= 3 OR CE.fechaCierre IS NULL)";
        final String EMP = "SELECT C.id, SD.cuentaReferencia 'IBAN', null, E.nombre, D.calle, D.ciudad, D.codigoPostal, D.pais, C.numeroIdentificacion, E.fechaRegistro, CE.fechaApertura, CE.fechaCierre, CE.estadoCuenta " +
                "FROM CuentaEbury CE, Cliente C, Empresa E, Direccion D, (SELECT * FROM Segregada UNION SELECT * FROM Dedicada) SD, CuentaBanco CB " +
                "WHERE " +
                "CE.id = SD.id AND C.id = CE.propietario AND D.cliente = C.id AND D.valida = 1 AND " +
                "C.id = E.cliente AND SD.cuentaReferencia = CB.ibanCuenta AND CB.pais = 'Holanda' AND (YEAR(CURDATE()) - YEAR(CE.fechaCierre) <= 3 OR CE.fechaCierre IS NULL)";


        try {
            System.out.println("BD cargada");
            BD myBD = new BD();
            List<Object[]> resultado;

            switch (str) {
                case "Nombre" -> {
                    System.out.println("Caso nombre");
                    resultado = myBD.Select(IND + " AND concat(P.nombre, ' ', COALESCE(P.segundoNombre, '')) LIKE '%" + text + "%';");
                    resultado.addAll(myBD.Select(EMP + " AND E.nombre LIKE '%" + text + "%';"));
                }
                case "Apellidos" -> {
                    System.out.println("Caso Apellidos");
                    resultado = myBD.Select(IND + " AND concat(P.apellido, ' ', COALESCE(P.segundoApellido, '')) LIKE '%" + text + "%';");
                }
                case "Fecha de inicio" -> {
                    System.out.println("Caso fechainicio");
                    resultado = myBD.Select(IND + " AND CE.fechaApertura LIKE '%" + text + "%';");
                    resultado.addAll(myBD.Select(EMP + " AND CE.fechaApertura LIKE '%" + text + "%';"));
                }
                case "Fecha de cierre" -> {
                    System.out.println("Caso fechacierre");
                    resultado = myBD.Select(IND + " AND CE.fechaCierre LIKE '%" + text + "%';");
                    resultado.addAll(myBD.Select(EMP + " AND CE.fechaCierre LIKE '%" + text + "%';"));
                }
                default -> {
                    System.out.println("Caso direccion");
                    resultado = myBD.Select(IND + " AND D.calle LIKE '%" + text + "%';");
                    resultado.addAll(myBD.Select(EMP + " AND D.calle LIKE '%" + text + "%';"));
                }
            }

            for(int i = 0; i < resultado.size(); i++) {
                List<Object[]> personasRelacionadas = myBD.Select(
                        "SELECT c2.numeroIdentificacion FROM PersonaRelacionada PR, Cliente c2, Cliente C " +
                                "WHERE PR.cliente = c2.id AND PR.clienteRelacionado = C.id AND " +
                                "C.id = " + Integer.parseInt(resultado.get(i)[0].toString()) + " AND PR.tipoClienteRelacionado = 'Autorizado';"
                );

                StringJoiner ps = new StringJoiner(", ");
                for (Object[] nombre : personasRelacionadas) {
                    ps.add((String)nombre[0]);
                }
                Object[] tupla = resultado.get(i);
                tupla = Arrays.copyOf(tupla, tupla.length + 1);
                tupla[tupla.length - 1] = ps;
                resultado.remove(i);
                resultado.add(i, tupla);
            }

            String[] nombreColumnas = {"IBAN", "Apellido", "Nombre", "Calle", "Ciudad", "CP", "País", "NIF", "Fecha de Nacimiento/Registro", "Fecha de Apertura", "Fecha de cierre", "Activa", "Personas relacionadas"};
            rellenarTabla(nombreColumnas, table, resultado);
        } catch (Exception e) {
            ErrorHolanda dialog = new ErrorHolanda();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }

    }
}
