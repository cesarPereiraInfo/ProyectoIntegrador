
package Core;
import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.ComandosUsuario;
import Modelo.Usuario;
import java.sql.*;

public class ControladorUsuario {
    ConexionMySQL c;

    public ControladorUsuario(){
        c=new ConexionMySQL();
    }
    public void agregarUsuario(Usuario pro) throws Exception{
        Statement t=null;
        String sql = ComandosUsuario.Insertar(pro);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
    public ResultSet UsuariosUno(int id) throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosUsuario.SeleccionarUsuario(id);
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
      public ResultSet UsuarioTodo() throws Exception{
        Statement t=null;
        ResultSet r;
        String sql=ComandosUsuario.todosLasUsuarios();
        t=c.abrir().createStatement();
        r=t.executeQuery(sql);
        return r;
    }
    public void actualizarUsuario(Usuario pro,int id) throws Exception{
        Statement t=null;
        String sql=ComandosUsuario.actualizar(pro,id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t.close();
        c.cerrar();
    }
   
    public void delete(int id) throws Exception{
        Statement t=null;
        String sql=ComandosUsuario.delete(id);
        t=c.abrir().createStatement();
        t.executeUpdate(sql);
        t=null;
        c.cerrar();
    }
   
    public ResultSet numeros(int id) throws Exception{
        ResultSet registros=null;
        Statement t=null;
        String sql=ComandosUsuario.numeros(id);
        c.cerrar();
        t=c.abrir().createStatement();
        registros=t.executeQuery(sql);
        return registros;
    }
    
  
}
