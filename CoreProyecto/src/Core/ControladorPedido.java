
package Core;

import BaseDeDatos.ConexionMySQL;
import Modelo.Pedido;
import java.sql.*;
import BaseDeDatos.ComandosPedidos;
import BaseDeDatos.ComandosProducto;
import Modelo.DetallePedido;

public class ControladorPedido {
    
    ConexionMySQL c;
    private ResultSet r;
    
    public ControladorPedido(){
        c = new ConexionMySQL();
        ResultSet r;
    }
    public void agregarPedido (Pedido pe) throws Exception{
        Statement t=null;
        String sql =  ComandosPedidos.insertarPedido(pe);
        t = c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
        
    }
    public void agregarDetallePedido (DetallePedido depe) throws Exception{
        Statement t=null;
        String sql =  ComandosPedidos.insertarDetalle(depe);
        t = c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
        
    }
    public ResultSet cargarDatos(String x) throws  Exception{
        Statement t = null;
        String sql = ComandosProducto.medicamentoPorNombre(x);
        t= c.abrir().createStatement();
        r = t.executeQuery(sql);
        
        return r;
    }
 public ResultSet obtenerFolio() throws Exception
    {
        Statement t=null;
        ResultSet r;
        String sql=ComandosPedidos.obtenerFolio();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    
    } 
  public ResultSet PedidoTodos() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPedidos.todasLosPedidos();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    } 
  public ResultSet todasLasPedidosEliminados() throws Exception{
        Statement t=null;
        ResultSet r=null;
        String sql=ComandosPedidos.todasLosPedidos_baja();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
        
    }
  public void delete(int id) throws Exception{
        Statement t=null;
        String sql=ComandosPedidos.delete(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
    public void activar(int id) throws Exception{
        Statement t=null;
        String sql=ComandosPedidos.altaLogico(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
    public ResultSet PeUno(int id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPedidos.seleccionarPedido(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public ResultSet v1() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPedidos.vistaSucursal1();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public ResultSet v2() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPedidos.vistaSucursal2();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
     public ResultSet v3() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPedidos.vistaSucursal3();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public ResultSet v4() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPedidos.vistaSucursal4();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
     public ResultSet ip(String id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPedidos.idPed(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
      public ResultSet fh(String id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosPedidos.fec(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    
}