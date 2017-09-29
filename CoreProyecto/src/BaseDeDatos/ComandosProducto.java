/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelo.producto;


public class ComandosProducto {
    public  static String Insertar(producto pro){
            String query="Insert Into producto (codigo_barras,nombreGeneral,nombreGenerico,formaFarmaceutica,concentracion,presentacion,precioUnidad,foto,rutaFoto,activo,unidadesEnvase,principalIndicacion,unidadMedida,contraindicaciones)"+"values('"+
                    pro.getNombreGeneral()+"','"+
                    pro.getNombreGenerico()+"','"+
                    pro.getFormaFarmaceutica()+"','"+
                    pro.getConcentracion()+"','"+
                    pro.getPresentacion()+"',"+
                    pro.getPrecioUnitario()+","+1+",'"+
                    pro.getFoto()+"','"+
                    pro.getRutaFoto()+"',"+
                    pro.getUnidadEnvases()+",'"+
                    pro.getPrincipalIndicacion()+"','"+
                    pro.getUnidadMedida()+"','"+
                    pro.getContraindicaciones()+"');";
            return query;
    }
    //scrip para seleccion de todos los productos
    public static String todosLosProductos(){
        String query="SELECT idProducto,nombreGeneral,nombreGenerico,formaFarmaceutica,concentracion,presentacion,precioUnidad,unidadesEnvase"
                +",principalIndicacion,unidadMedida,contraindicaciones,foto,rutaFoto from producto where activo=1";
        return query;
    }
    //scrip mysql para seleccionar todos los medicamentos eliminados logicamente
    public static String todosLosProductos_baja(){
        String query="SELECT idProducto,nombreGeneral,nombreGenerico,formaFarmaceutica,concentracion,presentacion,precioUnidad,unidadesEnvase"
                +",principalIndicacion,unidadMedida,contraindicaciones,foto,rutaFoto from producto where activo=0";
        return query;
    }
    
    
public static String SeleccionarProducto(int id){
    String query="SELECT idProducto,nombreGeneral,nombreGenerico,formaFarmaceutica,concentracion,presentacion,precioUnidad,unidadesEnvase"
    +",principalIndicacion,unidadMedida,contraindicaciones,foto,rutaFoto from producto Where idProducto="+id+" or nombreGeneral like '%"+id+"%'";
return query;
}
public static String actualizar(producto pro, int id){
  String query="UPDATE producto set nombreGeneral='"
          +pro.getNombreGeneral()+"',nombreGenerico='"+
          pro.getNombreGenerico()+"',formaFarmaceutica='"+
          pro.getFormaFarmaceutica()+"',concentracion='"+
          pro.getConcentracion()+"',presentacion='"+
          pro.getPresentacion()+"',precioUnidad="+
          pro.getPrecioUnitario()+",foto='"+
          pro.getFoto()+"',rutaFoto='"+
          pro.getRutaFoto()+ "',unidadesEnvase="+
          pro.getUnidadEnvases()+",principalIndicacion='"+
          pro.getPrincipalIndicacion()+"',unidadMedida='"+
          pro.getUnidadMedida()+"',contraindicaciones='"+
          pro.getContraindicaciones()+"' WHERE idProducto="+id;
  return query;
          
}
//Scrip para eliminar logicamente un medicamento.
public static String delete(int id){
    String query="UPDATE producto SET activo=0 WHERE idProducto="+id;
    return query;
}
//Scrip para activar logicamente 
public static String altaLogico(int id){
    String query="UPDATE producto SET activo=1 WHERE idProducto="+id;
    return query;
}

public static String medicamentoPorNombre(String letras){
        String query="SELECT idProducto,nombreGeneral,nombreGenerico,"
                +"precioUnidad,formaFarmaceutica,concentracion,activo"
                +" FROM producto WHERE activo=1"
                +" AND nombreGeneral like '"+letras+"%'";
        return query;
    }
 public static String datosColeccion(String l){
       String query="select idProducto,nombreGeneral,nombreGenerico,formaFarmaceutica,concentracion,presentacion,precioUnidad,unidadesEnvase,principalIndicacion,unidadMedida,contraindicaciones,foto,rutaFoto from producto where nombreGenerico like'"+l+"%'";
        return query;
    }
public static String numeros(int id){
       String query="select idProducto,nombreGeneral,nombreGenerico,formaFarmaceutica,concentracion,presentacion,precioUnidad,unidadesEnvase,principalIndicacion,unidadMedida,contraindicaciones,foto,rutaFoto from producto where idProducto like'%"+id+"'";
        return query;
    }
public static String todoswe(){
        String query="SELECT idProducto,nombreGeneral,nombreGenerico,formaFarmaceutica,concentracion,presentacion,precioUnidad,unidadesEnvase"
                +",principalIndicacion,unidadMedida,contraindicaciones,foto,rutaFoto from producto ";
        return query;
    }
}
