/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelo.Venta;
import Modelo.DetalleVenta;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author JaredXD
 */
public class ComandosVenta {

    public static void agregarVenta(Venta vta) throws Exception {
        String sqlVenta = "INSERT INTO venta(fechaHora, activo, idCliente, idEmpleado, idSucursal)"
                + "VALUES(NOW(),1,?,?,?)";
        String sqlDetalleVenta = "INSERT INTO detalleVenta(idProducto, idVenta, cantidad, precioVenta)"
                + "VALUES(?,?,?,?)";

        ConexionMySQL connMySQL = new ConexionMySQL();
        java.sql.Connection conn = connMySQL.abrir();

        java.sql.PreparedStatement pstmt = null;

        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        //Aqui guadaremps el ID generado de la Venta:
        int idVentaGenerado = 0;

        // Establecemos la conexión en modo "Manual":
        conn.setAutoCommit(false);

        try {
            // Generamos un PreparedStatement para ejecutar la consulta
            // Y le indicamos que nos va a devolver el Id que se generó:
            pstmt = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            //Establecemos los valores que se necesitan en la consulta:
            pstmt.setInt(1, vta.getCliente().getId());
            pstmt.setInt(2, vta.getEmpleado().getId());
            pstmt.setInt(3, vta.getEmpleado().getSucursal().getId());
            //Ejecutamos la consulta:
            pstmt.executeUpdate();
            //Extraemos el resultado:
            rs = pstmt.getResultSet();
            //Extraemos del ID de venta generado:
            rs.first();//Nos movemos al primer registro
            idVentaGenerado = rs.getInt(1);

            //Cerramos el ResultSet y el PreparedStatement:
            rs.close();
            pstmt.close();

            //Generamos un nuevo PreparedStatement:
            pstmt = conn.prepareStatement(sqlDetalleVenta);
            //Establecemos los valores de cd detalle de venta
            //y lo guardamos en la BD:
            for (int i = 0; i < vta.getProducto().length; i++) {
                pstmt.setInt(1, vta.getProducto()[i].getId());
                pstmt.setInt(2, idVentaGenerado);
                pstmt.setInt(3, vta.getProducto()[i].getCantidad());
                pstmt.setDouble(4, vta.getProducto()[i].getPrecioUnitario());

                pstmt.executeUpdate();
            }
            //Como no hubo errores, persistimos los cambios de forma definitiva en la BD:
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        }
        // Regresamos la conexión al modo "Automático":
        conn.setAutoCommit(true);
        //Cerramos los objetos de conexión con la BD:
        pstmt.close();
        connMySQL.cerrar();
    }

    public static String seleccionarVenta(int id) {
        String query = "select * from detalleVenta where idVenta=" + id + ";";
        return query;
    }

    public static String todasLasVentas() {
        String query = "select venta.idVenta,venta.fechaHora,concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),producto.nombreGeneral ,detalleVenta.cantidad,concat(detalleVenta.precioVenta*detalleVenta.cantidad),cliente.idCliente, sucursal.nombre  from empleado inner join venta on empleado.idEmpleado=venta.idEmpleado inner join cliente on cliente.idCliente=venta.idCliente inner join detalleVenta on venta.idVenta=detalleVenta.idVenta inner join producto on producto.idProducto=detalleVenta.idProducto inner join sucursal on sucursal.idSucursal=venta.idSucursal inner join persona on persona.idPersona=empleado.idPersona where venta.activo=1 order by venta.idVenta;";
        return query;
    }

    public static String todasLasVentas_baja() {
        String query = "select venta.idVenta,venta.fechaHora,concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),producto.nombreGeneral ,detalleVenta.cantidad,concat(detalleVenta.precioVenta*detalleVenta.cantidad),cliente.idCliente, sucursal.nombre  from empleado inner join venta on empleado.idEmpleado=venta.idEmpleado inner join cliente on cliente.idCliente=venta.idCliente inner join detalleVenta on venta.idVenta=detalleVenta.idVenta inner join producto on producto.idProducto=detalleVenta.idProducto inner join sucursal on sucursal.idSucursal=venta.idSucursal inner join persona on persona.idPersona=empleado.idPersona where venta.activo=0 order by venta.idVenta;";
        return query;
    }
    //Scrip para eliminar logicamente.

    public static String delete(int id) {
        String query = "UPDATE venta SET activo=0 WHERE idVenta=" + id;
        return query;
    }
     public static String  inventarioActual(String x,int idscur)
   {
       
        String query="select idproducto,nombregeneral,nombregenerico,"
            + "preciounidad,formafarmaceutica,concentracion,existencias,activo"
            + " from vinventariosucursalproducto where activo=1 "
            + "and nombregeneral like '"+x+"%' AND idsucursal = "+idscur;
       return(query);
   }
//Scrip para activar logicamente 

    public static String altaLogico(int id) {
        String query = "UPDATE venta SET activo=1 WHERE idVenta=" + id;
        return query;
    }

    public static String obtenerFolio() {
        String query = "SELECT max(idVenta) from venta";
        return query;
    }

    public static String vistaSucursal1() {
        String query = "select*from venta1;";
        return query;
    }

    public static String vistaSucursal2() {
        String query = "select*from venta2;";
        return query;
    }

    public static String vistaSucursal3() {
        String query = "select*from venta3;";
        return query;
    }

    public static String vistaSucursal4() {
        String query = "select*from venta4;";
        return query;
    }

    public static String mostrarTotal(int id) {
        String query = "select concat(detalleVenta.precioVenta*detalleVenta.cantidad) from detalleVenta where idVenta=" + id + ";";
        return query;
    }
}
