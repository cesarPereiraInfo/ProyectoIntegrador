/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;
import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.ComandosEmpleado;
import BaseDeDatos.ComandosUsuario;
import Modelo.Empleado;
import Modelo.Persona;
import Modelo.Sucursal;
import Modelo.Usuario;
import java.sql.*;
/**
 *
 * @author JaredXD
 */
public class ControladorEmpleado {
    ConexionMySQL c;
    ResultSet r;

    public ControladorEmpleado(){
        c=new ConexionMySQL();
    }
    public void agregarEmpleado(Empleado pro) throws Exception{
        Statement t=null;
        String sql = ComandosEmpleado.Insertar(pro);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public void agregarUsuario(Usuario usu) throws Exception{
        Statement t=null;
        String sql = ComandosUsuario.Insertar(usu);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public ResultSet EmpleadoUno(int id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosEmpleado.SeleccionarEmpleado(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
     public ResultSet UsuUno(int id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosEmpleado.SeleccionarUsuario(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public ResultSet EmpleadoTodo() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosEmpleado.todosLosEmpleados();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public void actualizarEmpleado(Empleado pro,int id) throws Exception{
        Statement t=null;
        String sql=ComandosEmpleado.actualizar(pro,id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public void actualizarU(Usuario usu,int id) throws Exception{
        Statement t=null;
        String sql=ComandosEmpleado.actualizarU(usu,id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public ResultSet todosLosEmpEliminados() throws Exception{
        Statement t=null;
        ResultSet r=null;
        String sql=ComandosEmpleado.todosLosEmpleados_baja();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
        
    }
    public void delete(int id) throws Exception{
        Statement t=null;
        String sql=ComandosEmpleado.delete(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
    public void activarEmpleado(int id) throws Exception{
        Statement t=null;
        String sql=ComandosEmpleado.altaLogico(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
    public ResultSet traerSucursal(int idEmpleado) throws SQLException, Exception{
        Statement t=null;
        String sql=ComandosEmpleado.tomarSucursal(idEmpleado);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        c.cerrar();
        return r;
    }
    
     public ResultSet datosColeccion(String l) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosEmpleado.datosColeccion(l);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }  
     public ResultSet numeros(String id) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosEmpleado.numeros(id);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
          public ResultSet fechas(String id) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosEmpleado.fecha(id);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
     public ResultSet consultarSucursales() throws Exception{
         Statement t=null;
         String sql = ComandosEmpleado.buscarSucursal();
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
     public ResultSet consultarPersona() throws Exception{
         Statement t=null;
         String sql = ComandosEmpleado.buscarPersona();
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
    public ResultSet consultarUsuario() throws Exception{
         Statement t=null;
         String sql = ComandosEmpleado.buscarUsuario();
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
     
    public void ce(String fecha, String rol) throws Exception{
         Statement t=null;
         String sql = ComandosEmpleado.codigoEmpleado(fecha,rol);
         t=c.abrir().createStatement();
         t.executeQuery(sql);
     }
    public ResultSet contarUsuario() throws Exception{
         Statement t=null;
         String sql=ComandosEmpleado.selectUsuario();
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
    public ResultSet traerUsuario(int idUsuario) throws Exception{
         Statement t=null;
         String sql=ComandosEmpleado.SeleccionarUsuario(idUsuario);
         t=c.abrir().createStatement();
         r=t.executeQuery(sql);
         return r;
     }
    public ResultSet buscarxcodigo(String codigo) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosEmpleado.busCod(codigo);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
    public ResultSet buscarxdo(String codigo) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosEmpleado.BUSCDOM(codigo);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
    public ResultSet buscarxcp(String codigo) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosEmpleado.BUSCCP(codigo);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
    public Empleado[] getAll(boolean mostrarEliminados, String filtro) throws Exception{
    //Aqui guardaremos los datos de los empleados devueltos por la consulta
    Empleado [] empleados = null;
    Empleado e = null;
    Persona p = null;
    Usuario u = null;
    Sucursal s = null;
    int index = 0;
    String sql = "select * from v_empleados";
    PreparedStatement pstmt = c.abrir().prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();
    
    //Intentamos movernos al ultimo registro para saber cuantos registros ha devuelto la consulta
        if (rs.last()) {
            empleados =new Empleado[rs.getRow()];
            //nos movemos alntes del primer registro para recorrer el ResultSet:
            rs.beforeFirst();
            while(rs.next()){
                //Llenamos los datos personales de la persona:
                p=new Persona();
                p.setApeMaterno(rs.getString("apeMaterno"));
                p.setApePaterno(rs.getString("apePaterno"));
                p.setCiudad(rs.getString("ciudad"));
                p.setCodigoPostal(rs.getString("codigoPostal"));
                p.setDomicilio(rs.getString("domicilio"));
                p.setEstado(rs.getString("estado"));
                p.setFechaNacimiento(rs.getString("fechaNacimiento"));
                p.setFoto(rs.getString("foto"));
                p.setGenero(rs.getInt("genero"));
                p.setId(rs.getInt("idPersona"));
                p.setNombre(rs.getString("nombrePersona"));
                p.setTelefono(rs.getString("telefono"));
                
                //Llenamos los datos del usuario:
                u=new Usuario();
                u.setContrasennia(rs.getString("contrasennia"));
                u.setId(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombreUsuario"));
                u.setRol(rs.getString("rol"));
                
                //Llenamos los datos de la sucursal:
                s = new Sucursal();
                s.setId(rs.getInt("idSucursal"));
                s.setNombre(rs.getString("nombreSucursal"));
                
                //Llenamos los datos del empleado:
                e = new Empleado();
                e.setActivo(rs.getInt("activo"));
                e.setCodigo(rs.getString("codigo"));
                e.setFechaIngreso(rs.getString("fechaIngreso"));
                e.setId(rs.getInt("idEmpleado"));
                e.setPuesto(rs.getString("puesto"));
                e.setSalario(rs.getFloat("salario"));
                e.setSucursal(s);
                e.setUsuario(u);
                e.setPersona(p);
                
                //insertamos el Empleado dentro del arreglo:
                empleados[index] = e;
                index++;
            }
        }
        rs.close();
        pstmt.close();
        c.cerrar();
    return empleados;}
}
