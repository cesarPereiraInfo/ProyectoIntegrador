/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Lolis
 */
public class producto {

    public producto(){}
    
    public producto(String nombreGenerico, String formaFarmaceutica, String unidadMedida, String presentacion, String principalIndicacion, String contraindicaciones, String concentracion, int unidadEnvases, String nombreGeneral, double precioUnitario, String foto,String rutafoto, boolean estatus) {
        this.nombreGenerico = nombreGenerico;
        this.formaFarmaceutica = formaFarmaceutica;
        this.unidadMedida = unidadMedida;
        this.presentacion = presentacion;
        this.principalIndicacion = principalIndicacion;
        this.contraindicaciones = contraindicaciones;
        this.concentracion = concentracion;
        this.unidadEnvases = unidadEnvases;
        this.nombreGeneral = nombreGeneral;
        this.precioUnitario = precioUnitario;
        this.foto = foto;
        this.rutaFoto=rutafoto;
        this.estatus = estatus;
    }

    int id;
    String nombreGenerico;
    String formaFarmaceutica;
    String unidadMedida;
    String presentacion;
    String principalIndicacion;
    String contraindicaciones;
    String concentracion;
    int unidadEnvases;
    String nombreGeneral;
    double precioUnitario;
    String foto;
    String rutaFoto;
    boolean estatus;
    int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getPrincipalIndicacion() {
        return principalIndicacion;
    }

    public void setPrincipalIndicacion(String principalIndicacion) {
        this.principalIndicacion = principalIndicacion;
    }

    public String getContraindicaciones() {
        return contraindicaciones;
    }

    public void setContraindicaciones(String contraindicaciones) {
        this.contraindicaciones = contraindicaciones;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public int getUnidadEnvases() {
        return unidadEnvases;
    }

    public void setUnidadEnvases(int unidadEnvases) {
        this.unidadEnvases = unidadEnvases;
    }

    public String getNombreGeneral() {
        return nombreGeneral;
    }

    public void setNombreGeneral(String nombreGeneral) {
        this.nombreGeneral = nombreGeneral;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    
}
