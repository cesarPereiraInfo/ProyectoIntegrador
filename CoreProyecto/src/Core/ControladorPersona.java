
package Core;
import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.ComandosPersona;
import Modelo.Persona;
import java.sql.*;

public class ControladorPersona {
    ConexionMySQL c;

    public ControladorPersona(){
        c=new ConexionMySQL();
    }
    public void agregarPersona(Persona pro) throws Exception{
        Statement t=null;
        String sql = ComandosPersona.Insertar(pro);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public ResultSet personasUno(int id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPersona.SeleccionarPersona(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
      public ResultSet PersonaTodo() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPersona.todosLasPersonas();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
      public ResultSet idPersona() throws Exception{
          Statement t=null;
          ResultSet r;
          String sql=ComandosPersona.idPersona();
          t=c.abrir().createStatement();
          r=t.executeQuery(sql);
          return r;
      }
    public void actualizarPersona(Persona pro,int id) throws Exception{
        Statement t=null;
        String sql=ComandosPersona.actualizar(pro,id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
   
    public void delete(int id) throws Exception{
        Statement t=null;
        String sql=ComandosPersona.delete(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
   public ResultSet datosColeccion(String l) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosPersona.datosColeccion(l);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
    public ResultSet numeros(int id) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosPersona.numeros(id);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
  
}
