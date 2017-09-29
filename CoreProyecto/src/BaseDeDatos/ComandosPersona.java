/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelo.Persona;


public class ComandosPersona {
    public  static String Insertar(Persona pro){
            String query="Insert Into persona (nombre,apePaterno,apeMaterno,genero,domicilio,codigoPostal,ciudad,estado,telefono,fechaNacimiento,foto)"+"values('"+
                    pro.getNombre()+"','"+
                    pro.getApePaterno()+"','"+
                    pro.getApeMaterno()+"',"+
                    pro.getGenero()+",'"+
                    pro.getDomicilio()+"','"+
                    pro.getCodigoPostal()+"','"+
                    pro.getCiudad()+"','"+
                    pro.getEstado()+"','"+
                    pro.getTelefono()+"','"+
                    pro.getFechaNacimiento()+"','"+
                    pro.getFoto()+"');";
            return query;
    }
 public static String todosLasPersonas(){
        String query="SELECT * from persona";
        return query;
    }
    
    
public static String SeleccionarPersona(int id){
    String query="SELECT * from persona Where idPersona="+id;
return query;
}
public static String actualizar(Persona pro, int id){
  String query="UPDATE persona set nombre='"
          +pro.getNombre()+"',apePaterno='"+
          pro.getApePaterno()+"',apeMaterno='"+
          pro.getApeMaterno()+"',genero="+
          pro.getGenero()+",domicilio='"+
          pro.getDomicilio()+"',codigoPostal='"+
          pro.getCodigoPostal()+"',ciudad='"+
          pro.getCiudad()+"',estado='"+
          pro.getEstado()+"',telefono='"+
          pro.getTelefono()+"',fechaNacimiento='"+
          pro.getFechaNacimiento()+"',foto='"+
           pro.getFoto()+"' WHERE idPersona="+id;
  return query;
          
}
//Scrip para eliminar logicamente una persona.
public static String delete(int id){
    String query="DELETE FROM persona WHERE idPersona="+id;
    return query;
}

 public static String datosColeccion(String l){
       String query="select * from persona where nombre like'"+l+"%'";
        return query;
    }
 public static String numeros(int id){
       String query="select * from persona where idPersona like'"+id+"%';";
        return query;
    }
public static String idPersona(){
    String query="select persona.idPersona, persona.nombre, persona.apePaterno from persona left join cliente on persona.idPersona=cliente.idPersona left join empleado on persona.idPersona=empleado.idPersona where cliente.idCliente is null and empleado.idEmpleado is null";
    return query;
}
}
