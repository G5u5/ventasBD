/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventasbd.aplicacion;

import java.text.SimpleDateFormat;
import ventasbd.dao.exception.ErrorConexionBD;
import java.util.Collection;
import ventasbd.modelo.Item;
import ventasbd.modelo.SistemaVentas;
import ventasbd.modelo.Venta;


public class VentasBD {
    public static void main(String[] args) {
        SistemaVentas sistemaVentas = null;
        try {
            sistemaVentas = new SistemaVentas();
        }
        catch(ErrorConexionBD e) {
            System.out.println("Error de conexión con BD");
            return;
        }
        
        
        Venta venta = sistemaVentas.venta(3);
        
        if (venta != null) {
            System.out.println("Venta num. " + Integer.toString(venta.getNum()) + 
                    " Fecha: " + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(venta.getFecha()));
        
            System.out.println("\nProductos:");
            Collection<Item> items = venta.getItems();

            for (Item i: items) {
                System.out.println(Integer.toString(i.getCantidad()) + " " + 
                        i.getDescripcion() + " " + Float.toString(i.getImporteUnidad()));   
            }
            System.out.println("\nSuma: " + Float.toString(venta.calcularImporte()));
            System.out.println("IVA: " + Float.toString(venta.calcularIVA()));
            System.out.println("Total: " + Float.toString(venta.calcularTotal()));
        }
        
        sistemaVentas.cerrar();
    }
}
