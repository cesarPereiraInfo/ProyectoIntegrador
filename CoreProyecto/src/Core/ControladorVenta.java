/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import BaseDeDatos.ConexionMySQL;
import Modelo.Venta;
import java.sql.*;
import BaseDeDatos.ComandosVenta;
import BaseDeDatos.ComandosProducto;
import Modelo.DetalleVenta;

/**
 *
 * @author JaredXD
 */
public class ControladorVenta {

    ConexionMySQL c;
    private ResultSet r;

    public ControladorVenta() {
        c = new ConexionMySQL();

    }
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

    public ResultSet VentaTodos() throws Exception {
        Statement t = null;
        ResultSet r;
        String sql = ComandosVenta.todasLasVentas();
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);
        return r;
    }

    public ResultSet todasLasVentasEliminados() throws Exception {
        Statement t = null;
        ResultSet r = null;
        String sql = ComandosVenta.todasLasVentas_baja();
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);
        return r;

    }

    public ResultSet cargarDatos(String x) throws Exception {
        Statement t = null;
        String sql = ComandosProducto.medicamentoPorNombre(x);
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);

        return r;
    }

    public ResultSet obtenerFolio() throws Exception {
        Statement t = null;
        ResultSet r;
        String sql = ComandosVenta.obtenerFolio();
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);
        return r;

    }

    public void delete(int id) throws Exception {
        Statement t = null;
        String sql = ComandosVenta.delete(id);
        t = c.abrir().createStatement();
        t.executeUpdate(sql);
        t = null;
        c.cerrar();
    }

    public void activarVenta(int id) throws Exception {
        Statement t = null;
        String sql = ComandosVenta.altaLogico(id);
        t = c.abrir().createStatement();
        t.executeUpdate(sql);
        t = null;
        c.cerrar();
    }

    public ResultSet VentasUno(int id) throws Exception {
        Statement t = null;
        ResultSet r;
        String sql = ComandosVenta.seleccionarVenta(id);
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);
        return r;
    }

    public ResultSet idventa1() throws Exception {
        Statement t = null;
        ResultSet r;
        String sql = ComandosVenta.vistaSucursal1();
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);
        return r;

    }

    public ResultSet idventa2() throws Exception {
        Statement t = null;
        ResultSet r;
        String sql = ComandosVenta.vistaSucursal2();
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);
        return r;

    }

    public ResultSet idventa3() throws Exception {
        Statement t = null;
        ResultSet r;
        String sql = ComandosVenta.vistaSucursal3();
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);
        return r;

    }

    public ResultSet idventa4() throws Exception {
        Statement t = null;
        ResultSet r;
        String sql = ComandosVenta.vistaSucursal4();
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);
        return r;

    }

    public ResultSet mt(int id) throws Exception {
        Statement t = null;
        ResultSet r;
        String sql = ComandosVenta.mostrarTotal(id);
        t = c.abrir().createStatement();
        r = t.executeQuery(sql);
        return r;
    }
}
