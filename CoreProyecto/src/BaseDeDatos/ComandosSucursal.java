
package BaseDeDatos;

import Modelo.Sucursal;


public class ComandosSucursal {
    
    
    public static String InsertarSucursal(Sucursal su){
        String query = "INSERT INTO sucursal (nombre, titular, domicilio, ciudad,"
                + "estado, codigoPostal, telefono, rfc, colonia) VALUES('"
                + su.getNombre() + "','"
                + su.getTitular() + "','"
                + su.getDomicilio() + "','"
                + su.getCiudad() + "','"
                + su.getEstado() + "','"
                + su.getCodigoPostal() + "','"
                + su.getTelefono() + "','"
                + su.getRfc() + "','"
                
                + su.getColonia() + "');";
                
                
        return query;        
    }
    
    public static String TodasLasSucursales(){
        String query = "SELECT idSucursal, nombre, titular,domicilio,"
                + "ciudad, estado, codigoPostal, telefono, rfc, colonia"
                + "FROM sucursal";
        return query;
    }
    
    public static String seleccionarSucursal( int id ){
        String query = "Select idSucursal,nombre, titular, domicilio"
                + ",ciudad, estado, codigoPostal, telefono, rfc, activo"
                + ",colonia from sucursal where idSucursal = " + id + ";";
        return query;
    }
    
    public static String actualizar(Sucursal su, String nombre){
        String query = "update sucursal set nombre='"
                + su.getNombre() + "', titular='"
                + su.getTitular() + "', domicilio='"
                + su.getDomicilio() + "', ciudad ='"
                + su.getCiudad() + "', estado = '"
                + su.getEstado() + "', codigoPostal ='"
                + su.getCodigoPostal() + "', telefono ='"
                + su.getTelefono() + "', rfc ='"
                + su.getRfc() + "', colonia  ='"
                + su.getColonia() + "' where nombre = " + nombre;
        return query;
        
        
    }
    
    public static String deleteLogico(int idSu){
       String query="update sucursal set activo=0;"+"where idSucursal="+idSu;
       return query;
   }
    
    public static String eliminarS(int id){
        String query = "delete from sucursal where idSucursal="+id;
        return query;
    }
    
    
    public static String altaLogico(int idSu){
       String query="update sucursal set activo=1;"+"where idSucursal="+idSu;
       return query;
   }
    
    
    public static String TodasLasSucursales_baja() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static String buscarSucursal(int idEmpleado){
        String query = "SELECT empleado.idSucursal, sucursal.nombre FROM empleado INNER JOIN sucursal on sucursal.idSucursal=empleado.idSucursal WHERE idEmpleado="+idEmpleado;
        return query;
    }
    public static String buscarEmpleado(){
        String query = "Select empleado.idEmpleado ,persona.nombre from empleado inner join persona on persona.idPersona=empleado.idPersona ";
        return query;
    }
    public static String buscarCliente(){
        String query = "Select cliente.idCliente ,persona.nombre, persona.apePaterno from cliente inner join persona on persona.idPersona=cliente.idPersona where cliente.activo=1 ";
        return query;
    }
    public static String insertarInventario(int idSucursal, int idProducto, int existencias){
        String query="call insertarInventario("+idProducto+","+idSucursal+","+existencias+");";
        return query;
    }
    
    
    
    
    
    
    
    
    
    
}
