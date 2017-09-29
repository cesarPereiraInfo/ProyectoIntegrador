/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;
import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.ComandosCliente;
import Modelo.Cliente;
import Modelo.Persona;
import java.sql.*;
/**
 *
 * @author JaredXD
 */
public class ControladorCliente {
    ConexionMySQL c;
    ResultSet r;

    public ControladorCliente(){
        c=new ConexionMySQL();
    }
    
    public Cliente[] getCliente(String filtro) throws Exception{
        String sql = "SELECT * FROM v_clientes";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cliente[] clientes = null;
        int indice = 0;
        
        if (filtro != null && !filtro.isEmpty()) {
            sql += "WHERE CONVERT(idPersona, CHAR) = ? OR "
                    + "nombre LIKE ? OR "
                    + "apePaterno LIKE ? OR "
                    + "apeMaterno LIKE ? OR"
                    + "CONVERT(idCliente, CHAR) = ? OR "
                    + "correoElectronico LIKE ?";
        }
        
        pstmt = c.abrir().prepareStatement(sql);
        //Si el filtro no es NULL y no está vacio,
        //establecemos los parametros del PraparedStatement;
        if (filtro != null && !filtro.isEmpty()) {
            for (int i = 0; i < 7; i++) {
                pstmt.setString(i, filtro);
            }
        }
        //Nos intentmos mover al último registro
        if (rs.last()) {
            //Obtenemos el número de registro y lo usmos para definir el tamaño del arreglo:
            clientes = new Cliente[rs.getRow()];
            //Nos movemos anres del primer registro para recorrer todo el ResultSet:
            rs.beforeFirst();
            //Recorremos el ResultSet:
            while(rs.next()){
                clientes[indice] = new Cliente();
                clientes[indice].setPersona(new Persona());
                clientes[indice].getPersona().setId(rs.getInt("idPesrona"));
                clientes[indice].getPersona().setNombre("nombre");
                clientes[indice].getPersona().setApePaterno("apePaterno");
                clientes[indice].getPersona().setApeMaterno("apeMaterno");
                clientes[indice].setId(rs.getInt("idCliente"));
                clientes[indice].setCorreo(rs.getString("correoElectronico"));
                indice++;
            }
        }
        //Cerramos todos los objetos de conexión:
        rs.close();
        pstmt.close();
        c.cerrar();
        //Devolvemos el arreglo de Cliente:
        return clientes;
    }
    
    public void agregarCliente(Cliente pro) throws Exception{
        Statement t=null;
        String sql = ComandosCliente.Insertar(pro);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public ResultSet ClienteUno(int id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosCliente.SeleccionarCliente(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public ResultSet ClienteTodo() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosCliente.todosLosClientes();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public void actualizarCliente(Cliente pro,int id) throws Exception{
        Statement t=null;
        String sql=ComandosCliente.actualizar(pro,id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public ResultSet todosLosClientesEliminados() throws Exception{
        Statement t=null;
        ResultSet r=null;
        String sql=ComandosCliente.todosLosClientes_baja();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
        
    }
    public void delete(int id) throws Exception{
        Statement t=null;
        String sql=ComandosCliente.delete(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
    public void activarCliente(int id) throws Exception{
        Statement t=null;
        String sql=ComandosCliente.altaLogico(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
    
     public ResultSet datosColeccion(String l) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosCliente.datosColeccion(l);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
      public ResultSet consultarCliente() throws Exception{
         Statement t=null;
         String sql = ComandosCliente.buscarCliente();
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
       public ResultSet numeros(int id) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosCliente.numeros(id);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
        public ResultSet consultarSucursales() throws Exception{
         Statement t=null;
         String sql = ComandosCliente.buscarSucursal();
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
     public ResultSet consultarPersona() throws Exception{
         Statement t=null;
         String sql = ComandosCliente.buscarPersona();
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
}
