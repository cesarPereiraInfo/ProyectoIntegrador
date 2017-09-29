
package Core;

import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.ComandosSucursal;
import Modelo.Sucursal;
import java.sql.*;

public class ControladorSucursal {
    ConexionMySQL c;
    ResultSet r;
    
     public ControladorSucursal(){
        c=new ConexionMySQL();
    }
     
     public void agregarSucursal(Sucursal su) throws Exception{
        Statement t=null;
        String sql=ComandosSucursal.InsertarSucursal(su);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
        
    }
     
     public ResultSet sucursalesTodas() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosSucursal.TodasLasSucursales();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
     
     public ResultSet sucursalesUno(int id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosSucursal.seleccionarSucursal(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
}
     
     public void actualizarSucursal(Sucursal su,String nombre) throws Exception{
        Statement t=null;
        String sql=ComandosSucursal.actualizar(su, nombre);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
     
     public void eliminarSucursal(int id)throws Exception
    {
        Statement t=null;
        ResultSet r;
        String sql=ComandosSucursal.eliminarS(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        c.cerrar();
    }
     
     
     public ResultSet consultarSucursales(int idEmpleado) throws Exception{
         Statement t=null;
         String sql = ComandosSucursal.buscarSucursal(idEmpleado);
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
      public ResultSet consultarEmpleado() throws Exception{
         Statement t=null;
         String sql = ComandosSucursal.buscarEmpleado();
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
      public ResultSet consultarCliente() throws Exception{
         Statement t=null;
         String sql = ComandosSucursal.buscarCliente();
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
      public void insertarEnInventario(int idSucursal, int idProducto, int existencias) throws Exception{
          Statement t=null;
          String sql="INSERT INTO `farmacia`.`inventario` (`idProducto`, `idSucursal`, `existencias`) VALUES ('"+idProducto+"', "
                  + "'"+idSucursal+"', '"+existencias+"');";
          t=c.abrir().createStatement();
          r=t.executeQuery(sql);
      }
    
}
