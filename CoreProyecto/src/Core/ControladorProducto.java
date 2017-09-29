
package Core;
import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.ComandosProducto;
import Modelo.producto;
import java.sql.*;
/**
 *
 * @author Roberto
 */
public class ControladorProducto {
    ConexionMySQL c;

    public ControladorProducto(){
        c=new ConexionMySQL();
    }
    public void agregarProducto(producto pro) throws Exception{
        Statement t=null;
        String sql = ComandosProducto.Insertar(pro);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public ResultSet productosUno(int id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosProducto.SeleccionarProducto(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public ResultSet ProductosTodo() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosProducto.todosLosProductos();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public void actualizarProducto(producto pro,int id) throws Exception{
        Statement t=null;
        String sql=ComandosProducto.actualizar(pro,id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public ResultSet todosLosProductosEliminados() throws Exception{
        Statement t=null;
        ResultSet r=null;
        String sql=ComandosProducto.todosLosProductos_baja();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
        
    }
    public void delete(int id) throws Exception{
        Statement t=null;
        String sql=ComandosProducto.delete(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
    public void activarProducto(int id) throws Exception{
        Statement t=null;
        String sql=ComandosProducto.altaLogico(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
    public ResultSet datosColeccion(String l) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosProducto.datosColeccion(l);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
    public ResultSet numeros(int id) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosProducto.numeros(id);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
   public ResultSet TodoXD() throws Exception{
        Statement t=null;
        ResultSet r=null;
        String sql=ComandosProducto.todoswe();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
   }
    
    
}
