/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelo.Cliente;

/**
 *
 * @author JaredXD
 */
public class ComandosCliente {

    public static String Insertar(Cliente pro) {
        String query = "INSERT INTO cliente(correoElectronico, fechaRegistro ,idPersona, idSucursal)" + "values('"
                + pro.getCorreo() + "','"
                + pro.getFechaRegistro() + "',"
                + pro.getPersonas() + ","
                + pro.getSucursales() + ");";
        return query;
    }
    //scrip para seleccion de todos los empleados

    public static String todosLosClientes() {
        String query = "SELECT cliente.idCliente, persona.nombre, persona.apePaterno, persona.apeMaterno, cliente.correoElectronico,"
                + "cliente.fechaRegistro from persona inner join cliente on persona.idPersona=cliente.idPersona where activo=1;";
        return query;
    }

    //scrip mysql para seleccionar todos los empleados eliminados logicamente
    public static String todosLosClientes_baja() {
        String query = "SELECT cliente.idCliente, persona.nombre, persona.apePaterno, persona.apeMaterno, cliente.correoElectronico,"
                + "cliente.fechaRegistro from persona inner join cliente on persona.idPersona=cliente.idPersona where activo=0;";
        return query;
    }

    public static String SeleccionarCliente(int id) {
        String query = "SELECT * from cliente Where idCliente=" + id;
        return query;
    }

    public static String actualizar(Cliente pro, int id) {
        String query = "UPDATE cliente set correoElectronico='"
                + pro.getCorreo() + "',fechaRegistro='"
                + pro.getFechaRegistro() + "',idPersona="
                + pro.getPersonas() + ",idSucursal="
                + pro.getSucursales() + " WHERE idCliente=" + id;
        return query;

    }
//Scrip para eliminar logicamente.

    public static String delete(int id) {
        String query = "UPDATE cliente SET activo=0 WHERE idCliente=" + id;
        return query;
    }
//Scrip para activar logicamente 

    public static String altaLogico(int id) {
        String query = "UPDATE cliente SET activo=1 WHERE idCliente=" + id;
        return query;
    }

    public static String datosColeccion(String l) {
        String query = "SELECT cliente.idCliente, persona.nombre, persona.apePaterno, persona.apeMaterno, cliente.correoElectronico,"
                + "cliente.fechaRegistro from persona inner join cliente on persona.idPersona=cliente.idPersona where persona.nombre like'" + l + "%'";
        return query;
    }

    public static String buscarCliente() {
        String query = "Select cliente.idCliente ,persona.nombre from cliente inner join persona on persona.idPersona=cliente.idPersona ";
        return query;
    }

    public static String numeros(int id) {
        String query = "SELECT cliente.idCliente, persona.nombre, persona.apePaterno, persona.apeMaterno, cliente.correoElectronico,"
                + "cliente.fechaRegistro from persona inner join cliente on persona.idPersona=cliente.idPersona where cliente.idCliente like'%" + id + "'";
        return query;
    }

    public static String buscarSucursal() {
        String query = "Select idSucursal,nombre from sucursal "
                + " where activo = 1";
        return query;
    }

    public static String buscarPersona() {
        String query = "select persona.idPersona, persona.nombre, persona.apePaterno from persona left join cliente on persona.idPersona=cliente.idPersona left join empleado on persona.idPersona=empleado.idPersona where cliente.idCliente is null and empleado.idEmpleado is null";
        return query;
    }

}
