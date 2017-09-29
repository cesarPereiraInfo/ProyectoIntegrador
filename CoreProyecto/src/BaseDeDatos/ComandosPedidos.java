
package BaseDeDatos;

import Modelo.DetallePedido;
import Modelo.Pedido;

public class ComandosPedidos {
    
    
    public static String insertarPedido(Pedido pe){
        String query="INSERT INTO pedido (fechaHoraPedido, estatus"
                    +",activo,idEmpleado"+",idSucursal) VALUES('"
                    +pe.getFechaHoraPedido()+"',"
                    +pe.getEstatus()+","+1+","
                    +pe.getIdEmpleado()+","
                    +pe.getIdSucursal()+");";
        return query;
    }
    
    public static String insertarDetalle(DetallePedido depe){
        String query="INSERT INTO detallePedido (idPedido,idProducto,precioCompra, cantidad) Values("
                    +depe.getIdPedido()+","
                    +depe.getIdProducto()+","
                    +depe.getPrecioCompra()+","
                    +depe.getCantidad()+");";
        return query;
    }
    
    public static String obtenerFolio()
    {
        String query="SELECT max(idPedido) from pedido";
        return query;
    }
    public static String todasLosPedidos(){
        String query="select pedido.idPedido, pedido.fechaHoraPedido, concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),producto.nombreGeneral, concat(detallePedido.precioCompra*detallePedido.cantidad), sucursal.nombre, detallePedido.cantidad from persona inner join empleado on persona.idPersona=empleado.idPersona inner join pedido on empleado.idEmpleado=pedido.idEmpleado inner join sucursal on sucursal.idSucursal=pedido.idSucursal inner join detallePedido on pedido.idPedido=detallePedido.idPedido inner join producto on producto.idProducto=detallePedido.idProducto where pedido.activo=1 order by pedido.idPedido;";
        return query;
    }
    public static String todasLosPedidos_baja(){
       String query="select pedido.idPedido, pedido.fechaHoraPedido, concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),producto.nombreGeneral, concat(detallePedido.precioCompra*detallePedido.cantidad), sucursal.nombre, detallePedido.cantidad from persona inner join empleado on persona.idPersona=empleado.idPersona inner join pedido on empleado.idEmpleado=pedido.idEmpleado inner join sucursal on sucursal.idSucursal=pedido.idSucursal inner join detallePedido on pedido.idPedido=detallePedido.idPedido inner join producto on producto.idProducto=detallePedido.idProducto where pedido.activo=0 order by pedido.idPedido;";
        return query;
    }
    //Scrip para eliminar logicamente.
public static String delete(int id){
    String query="UPDATE pedido SET activo=0 WHERE idPedido="+id;
    return query;
}
//Scrip para activar logicamente 
public static String altaLogico(int id){
        String query="UPDATE pedido SET activo=1 WHERE idPedido="+id;
    return query;
}
public static String seleccionarPedido(int id){
           String query="select * from detallePedido where idPedido="+id+";";
           return query;
      }
public static String vistaSucursal1(){
    String query="select*from s1;";
    return query;
}
public static String vistaSucursal2(){
    String query="select*from s2;";
    return query;
}
public static String vistaSucursal3(){
    String query="select*from s3;";
    return query;
}
public static String vistaSucursal4(){
    String query="select*from s4;";
    return query;
}
public static String idPed(String id){
    String query="select pedido.idPedido, pedido.fechaHoraPedido, concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),producto.nombreGeneral, concat(detallePedido.precioCompra*detallePedido.cantidad), sucursal.nombre, detallePedido.cantidad from persona inner join empleado on persona.idPersona=empleado.idPersona inner join pedido on empleado.idEmpleado=pedido.idEmpleado inner join sucursal on sucursal.idSucursal=pedido.idSucursal inner join detallePedido on pedido.idPedido=detallePedido.idPedido inner join producto on producto.idProducto=detallePedido.idProducto where pedido.idPedido like '"+id+"%' ;";
return query;
}
public static String fec(String id){
    String query="select pedido.idPedido, pedido.fechaHoraPedido, concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),producto.nombreGeneral, concat(detallePedido.precioCompra*detallePedido.cantidad), sucursal.nombre, detallePedido.cantidad from persona inner join empleado on persona.idPersona=empleado.idPersona inner join pedido on empleado.idEmpleado=pedido.idEmpleado inner join sucursal on sucursal.idSucursal=pedido.idSucursal inner join detallePedido on pedido.idPedido=detallePedido.idPedido inner join producto on producto.idProducto=detallePedido.idProducto where pedido.fechaHoraPedido like '"+id+"%'  ;";
return query;
}
}
