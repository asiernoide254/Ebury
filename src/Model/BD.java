package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD {
    private Connection con;
    private final String URL = "jdbc:mysql://eburyrequisitos.cobadwnzalab.eu-central-1.rds.amazonaws.com";
    private final String USER = "grupo12";
    private final String PSW = "TtkuXmB872ZbygTR";

    public BD () throws SQLException {
        con = DriverManager.getConnection(URL,USER,PSW);
    }
    protected void finalize () throws Throwable
    {
        try
        {
            if (con!=null)  con.close();
        }
        catch (SQLException ex)
        {
            throw new Error("Error al Cerrar la Conexión." + ex.getMessage());
        }
    }

    public Object SelectEscalar(String sel)
    {
        ResultSet rset;
        Object res = null;
        try
        {
            Statement stmt = con.createStatement();
            rset = stmt.executeQuery(sel);
            rset.next();
            res = rset.getObject(1);
            rset.close();
            stmt.close();
        }
        catch (SQLException ex)
        {
            throw new Error("Error en el SELECT: " + sel + ". " + ex.getMessage());
        }

        return res;
    }

    public List<Object[]> Select(String sel)
    {
        ResultSet rset;
        List<Object[]> lista = new ArrayList<Object[]>();
        try
        {
            Statement stmt = con.createStatement();
            rset = stmt.executeQuery(sel);
            ResultSetMetaData meta = rset.getMetaData();
            int numCol = meta.getColumnCount();
            while (rset.next())
            {
                Object[] tupla = new Object[numCol];
                for(int i=0; i<numCol;++i)
                {
                    tupla[i] = rset.getObject(i+1);
                }
                lista.add(tupla);
            }
            rset.close();
            stmt.close();
        }
        catch (SQLException ex)
        {
            throw new Error("Error en el SELECT: " + sel + ". " + ex.getMessage() );
        }

        return lista;
    }

    //Modify() incluye Insert, Update y Delete
    public void Modify(String ins) throws SQLException {
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(ins);
            stmt.close();
        }
        catch (SQLException ex)
        {
            throw new SQLException("Error en la sentencia SQL: " + ins+ ". " + ex.getMessage());
        }
    }

}
