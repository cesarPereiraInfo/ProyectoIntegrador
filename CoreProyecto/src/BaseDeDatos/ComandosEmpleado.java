/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelo.Empleado;
import Modelo.Usuario;
/**
 *
 * @author JaredXD
 */
public class ComandosEmpleado {
     public  static String Insertar(Empleado pro){
            String query="INSERT INTO empleado(codigo,fechaIngreso,puesto,salario,idPersona,idUsuario, idSucursal)"+"values('"+
                    pro.getCodigo()+"','"+
                    pro.getFechaIngreso()+"','"+
                    pro.getPuesto()+"',"+
                    pro.getSalario()+","+
                    pro.getPersonas()+","+
                    pro.getUsuarios()+","+
                    pro.getSucursales()+");";
            return query;
     }
         public  static String Insertar(Usuario usu){
            String query="Insert Into usuario (nombre,contrasennia, rol)"+"values('"+
                    usu.getNombre()+"','"+
                    usu.getContrasennia()+"','"+
                    usu.getRol()+"');";
            return query;
    }
       //scrip para seleccion de todos los empleados
    public static String todosLosEmpleados(){
        String query="SELECT empleado.idEmpleado, persona.nombre, persona.apePaterno, persona.apeMaterno, empleado.codigo, empleado.fechaIngreso,persona.codigoPostal,persona.domicilio," +
"empleado.puesto, empleado.salario from persona inner join empleado on persona.idPersona=empleado.idPersona where activo=1;";
        return query;
    }
    //scrip mysql para seleccionar todos los empleados eliminados logicamente
    public static String todosLosEmpleados_baja(){
        String query="SELECT empleado.idEmpleado, persona.nombre, persona.apePaterno, persona.apeMaterno, empleado.codigo, empleado.fechaIngreso,persona.codigoPostal,persona.domicilio," +
"empleado.puesto, empleado.salario from persona inner join empleado on persona.idPersona=empleado.idPersona where activo=0;";
        return query;
    }
    
    
public static String SeleccionarEmpleado(int id){
    String query="SELECT * from empleado Where idEmpleado="+id;
return query;
}
public static String actualizar(Empleado pro, int id){
  String query="UPDATE empleado set codigo='"
          +pro.getCodigo()+"',fechaIngreso='"+
          pro.getFechaIngreso()+"',puesto='"+
          pro.getPuesto()+"',salario="+
          pro.getSalario()+",idPersona="+
          pro.getPersonas()+",idUsuario="+
          pro.getUsuarios()+",idSucursal="+
          pro.getSucursales()+" WHERE idEmpleado="+id;
  return query;
          
}
public static String SeleccionarUsuario(int id){
    String query="SELECT * from usuario Where idUsuario="+id;
return query;
}
public static String actualizarU(Usuario pro, int id){
  String query="UPDATE usuario set nombre='"
          +pro.getNombre()+"',contrasennia='"+
          pro.getContrasennia()+"',rol='"+
          pro.getRol()+"' WHERE idUsuario="+id;
  return query;
          
}
//Scrip para eliminar logicamente.
public static String delete(int id){
    String query="UPDATE empleado SET activo=0 WHERE idEmpleado="+id;
    return query;
}
//Scrip para activar logicamente 
public static String altaLogico(int id){
        String query="UPDATE empleado SET activo=1 WHERE idEmpleado="+id;
    return query;
}
public static String datosColeccion(String l){
       String query="SELECT empleado.idEmpleado, persona.nombre, persona.apePaterno, persona.apeMaterno, empleado.codigo, empleado.fechaIngreso,persona.codigoPostal,persona.domicilio," +
"empleado.puesto, empleado.salario from persona inner join empleado on persona.idPersona=empleado.idPersona where persona.nombre like'"+l+"%'";
        return query;
    }
public static String numeros(String id){
       String query="SELECT empleado.idEmpleado, persona.nombre, persona.apePaterno, persona.apeMaterno, empleado.codigo, empleado.fechaIngreso,persona.codigoPostal,persona.domicilio," +
"empleado.puesto, empleado.salario from persona inner join empleado on persona.idPersona=empleado.idPersona where empleado.idEmpleado like'%"+id+"'";
        return query;
    }
public static String fecha(String id){
       String query="SELECT empleado.idEmpleado, persona.nombre, persona.apePaterno, persona.apeMaterno, empleado.codigo, empleado.fechaIngreso, persona.codigoPostal,persona.domicilio," +
"empleado.puesto, empleado.salario from persona inner join empleado on persona.idPersona=empleado.idPersona where empleado.fechaIngreso like'%"+id+"'";
        return query;
    }
public static String buscarSucursal(){
        String query = "Select idSucursal,nombre from sucursal "
                + " where activo = 1";
        return query;
    }
public static String buscarPersona(){
    String query= "select persona.idPersona, persona.nombre, persona.apePaterno from persona left join cliente on persona.idPersona=cliente.idPersona left join empleado on persona.idPersona=empleado.idPersona where cliente.idCliente is null and empleado.idEmpleado is null";
    return query;
}
public static String buscarUsuario(){
    String query= "Select idUsuario,nombre from usuario";
    return query;
}
public static String codigoEmpleado(String fechaIngreso,String rol){
    String query=" call insertarCodigo('"+fechaIngreso+"','"+rol+"')";
    return query;
}
public static String selectUsuario(){
    String query="select count(idUsuario) from usuario";
    return query;
}
public static String seleccionarUsuario(int idUsuario){
    String query="SELECT nombre FROM usuario WHERE idUsuario=17";
    return query;
}
public static String tomarSucursal(int idEmpleado){
    String query="SELECT empleado.idSucursal, sucursal.nombre FROM empleado INNER JOIN sucursal on sucursal.idSucursal=empleado.idSucursal WHERE idEmpleado="+idEmpleado;
    return query;
}
public static String busCod(String codigo){
       String query="SELECT empleado.idEmpleado, persona.nombre, persona.apePaterno, persona.apeMaterno, empleado.codigo, empleado.fechaIngreso,persona.codigoPostal,persona.domicilio," +
"empleado.puesto, empleado.salario from persona inner join empleado on persona.idPersona=empleado.idPersona where empleado.codigo like'"+codigo+"%'";
        return query;
    }
public static String BUSCDOM(String codigo){
       String query="SELECT empleado.idEmpleado, persona.nombre, persona.apePaterno, persona.apeMaterno, empleado.codigo, empleado.fechaIngreso,persona.codigoPostal,persona.domicilio," +
"empleado.puesto, empleado.salario from persona inner join empleado on persona.idPersona=empleado.idPersona where persona.domicilio like'"+codigo+"%'";
        return query;
    }
public static String BUSCCP(String codigo){
       String query="SELECT empleado.idEmpleado, persona.nombre, persona.apePaterno, persona.apeMaterno, empleado.codigo, empleado.fechaIngreso,persona.codigoPostal,persona.domicilio," +
"empleado.puesto, empleado.salario from persona inner join empleado on persona.idPersona=empleado.idPersona where persona.codigoPostal like'"+codigo+"%'";
        return query;
    }
}
