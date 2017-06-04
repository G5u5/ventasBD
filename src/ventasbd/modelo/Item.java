/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventasbd.modelo;

/**
 *
 * @author ajrueda
 */
public class Item {
    int cantidad;
    String descripcion;
    float importeUnidad;
    
    public Item(int cantidad, String descripcion, float importeUnidad) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.importeUnidad = importeUnidad;
    }
    
    public String obtenerCantidad(){
        
        return Integer.toString(cantidad);
        
    }
    
    public int getCantidad() { 
        return cantidad; 
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
     public String obtenerImporteUnidad(){
        
        return Float.toString(importeUnidad);
        
    }
    public float getImporteUnidad() {
        return importeUnidad;
    }
    
    public float importe() {
        return cantidad * importeUnidad;
    }
}
