package Core;

import java.sql.ResultSet;
import java.sql.Statement;
import BaseDeDatos.ConexionMySQL;
import Modelo.Empleado;
import Modelo.Persona;
import Modelo.Sucursal;
import Modelo.Usuario;


public class ControllerLogin
{
    int idEmpleado;
    String rol;
     public Empleado getAll(Empleado idEmpleado)
    {
        return idEmpleado;
    }
    
    /*
        Aunque el requerimiento originalmente decía que un usuario se iba a
        validar con nombre de usuario, contraseña y sucursal,
        al final, se decidió que solo se use username y contrasenia
    */
    public Empleado validarUsuario(String userName, String password) throws Exception
    {
        String query = "SELECT  P.idPersona, P.nombre, P.apePaterno, P.apeMaterno, " + 
                               "P.genero, P.domicilio, P.codigoPostal, P.ciudad, " + 
                               "P.estado, P.telefono, P.fechaNacimiento, P.foto, " +
                               "S.idSucursal, S.nombre, S.titular, S.domicilio, " +
                               "S.ciudad, S.estado, S.codigoPostal, S.telefono, " +
                               "S.rfc, S.activo AS sucursalActivo, S.colonia, " +
                               "U.idUsuario, U.nombre, U.contrasennia, U.rol, " +
                               "E.idEmpleado, E.codigo, E.fechaIngreso, E.puesto, " +
                               "E.salario, E.activo AS empleadoActivo, E.idPersona, E.idUsuario, " +
                               "E.idSucursal " +
                       "FROM    persona P INNER JOIN empleado E ON P.idPersona = E.idPersona " +
                               "INNER JOIN usuario U ON U.idUsuario = E.idUsuario " +
                               "INNER JOIN sucursal S ON S.idSucursal = E.idSucursal " +
                       "WHERE   U.nombre = '" + userName + "' AND " + 
                               "U.contrasennia = '" + password + "'";
        ConexionMySQL cmysql = new ConexionMySQL();
        Statement stmt = cmysql.abrir().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        Persona p = null;
        Usuario u = null;
        Sucursal s = null;
        Empleado e = null;
        
        if (rs.next())
        {
            p = new Persona();
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
            p.setNombre(rs.getString("nombre"));
            p.setTelefono(rs.getString("telefono"));
            
            s = new Sucursal();
            s.setActivo(rs.getInt("sucursalActivo"));
            s.setCiudad(rs.getString("ciudad"));
            s.setCodigoPostal(rs.getString("codigoPostal"));
            s.setColonia(rs.getString("colonia"));
            s.setDomicilio(rs.getString("domicilio"));
            s.setEstado(rs.getString("estado"));
            s.setId(rs.getInt("idSucursal"));
            s.setNombre(rs.getString("nombre"));
            s.setRfc(rs.getString("rfc"));
            s.setTelefono(rs.getString("telefono"));
            s.setTitular(rs.getString("titular"));
            
            u = new Usuario();
            u.setContrasennia(rs.getString("contrasennia"));
            u.setId(rs.getInt("idUsuario"));
            u.setNombre(rs.getString("nombre"));
            u.setRol(rs.getString("rol"));
            
            e = new Empleado();
            e.setActivo(rs.getInt("empleadoActivo"));
            e.setCodigo(rs.getString("codigo"));
            e.setId(rs.getInt("idEmpleado"));            
            e.setPuesto(rs.getString("puesto"));
            e.setSalario(rs.getFloat("salario"));
            e.setPersona(p);
            e.setSucursal(s);
            e.setUsuario(u);
        }
        
        rs.close();
        stmt.close();
        cmysql.cerrar();
        
        return e;
    }
    public Usuario tomarRol(String userName) throws Exception
    {
        String query = "SELECT  P.idPersona, P.nombre, P.apePaterno, P.apeMaterno, " + 
                               "P.genero, P.domicilio, P.codigoPostal, P.ciudad, " + 
                               "P.estado, P.telefono, P.fechaNacimiento, P.foto, " +
                               "S.idSucursal, S.nombre, S.titular, S.domicilio, " +
                               "S.ciudad, S.estado, S.codigoPostal, S.telefono, " +
                               "S.rfc, S.activo AS sucursalActivo, S.colonia, " +
                               "U.idUsuario, U.nombre, U.contrasennia, U.rol, " +
                               "E.idEmpleado, E.codigo, E.fechaIngreso, E.puesto, " +
                               "E.salario, E.activo AS empleadoActivo, E.idPersona, E.idUsuario, " +
                               "E.idSucursal " +
                       "FROM    persona P INNER JOIN empleado E ON P.idPersona = E.idPersona " +
                               "INNER JOIN usuario U ON U.idUsuario = E.idUsuario " +
                               "INNER JOIN sucursal S ON S.idSucursal = E.idSucursal " +
                       "WHERE   U.nombre = '" + userName + "'";
        ConexionMySQL cmysql = new ConexionMySQL();
        Statement stmt = cmysql.abrir().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        Persona p = null;
        Usuario u = null;
        Sucursal s = null;
        Empleado e = null;
        
        if (rs.next())
        {
            p = new Persona();
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
            p.setNombre(rs.getString("nombre"));
            p.setTelefono(rs.getString("telefono"));
            
            s = new Sucursal();
            s.setActivo(rs.getInt("sucursalActivo"));
            s.setCiudad(rs.getString("ciudad"));
            s.setCodigoPostal(rs.getString("codigoPostal"));
            s.setColonia(rs.getString("colonia"));
            s.setDomicilio(rs.getString("domicilio"));
            s.setEstado(rs.getString("estado"));
            s.setId(rs.getInt("idSucursal"));
            s.setNombre(rs.getString("nombre"));
            s.setRfc(rs.getString("rfc"));
            s.setTelefono(rs.getString("telefono"));
            s.setTitular(rs.getString("titular"));
            
            u = new Usuario();
            u.setContrasennia(rs.getString("contrasennia"));
            u.setId(rs.getInt("idUsuario"));
            u.setNombre(rs.getString("nombre"));
            u.setRol(rs.getString("rol"));
            
            e = new Empleado();
            e.setActivo(rs.getInt("empleadoActivo"));
            e.setCodigo(rs.getString("codigo"));
            e.setId(rs.getInt("idEmpleado"));            
            e.setPuesto(rs.getString("puesto"));
            e.setSalario(rs.getFloat("salario"));
            e.setPersona(p);
            e.setSucursal(s);
            e.setUsuario(u);
        }
        
        rs.close();
        stmt.close();
        cmysql.cerrar();
        
        return u;
    }
    
//    public Usuario obtenerUsuario(){
//        
//    }
    
        public int idEmpleado(){
        return idEmpleado;
    }
}
