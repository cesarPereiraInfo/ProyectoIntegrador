/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelo.Usuario;



public class ComandosUsuario {
    public  static String Insertar(Usuario pro){
            String query="Insert Into usuario (nombre,contrasennia, rol)"+"values('"+
                    pro.getNombre()+"','"+
                    pro.getContrasennia()+"','"+
                    pro.getRol()+"');";
            return query;
    }
 public static String todosLasUsuarios(){
        String query="SELECT *FROM usuario";
        return query;
    }
    
    
public static String SeleccionarUsuario(int id){
    String query="SELECT * from usuario Where idUsuario="+id;
return query;
}
public static String actualizar(Usuario usu, int id){
  String query="UPDATE usuario set nombre='"
          +usu.getNombre()+"',contrasennia='"+
          usu.getContrasennia()+"',rol='"+
          usu.getRol()+"' WHERE idUsuario="+id;
  return query;
          
}
//Scrip para eliminar logicamente una persona.
public static String delete(int id){
    String query="DELETE FROM usuario WHERE idUsuario="+id;
    return query;
}

public static String numeros(int id){
       String query="SELECT * from usuario where idUsuario like'%"+id+"'";
        return query;
    }


}
