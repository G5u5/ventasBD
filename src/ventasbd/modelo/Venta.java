/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventasbd.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

class ErrorCreacionItem extends Exception {}

public class Venta {
    int num;
    Date fecha;
    ArrayList<Item> items;
    
    public Venta(int num) {
        this.num = num;
        fecha = new Date();
        items = new ArrayList<Item>();
    }
    
    public void anadirItem(Item i) throws ErrorCreacionItem {
        items.add(i);
    }
    
    public int getNum() {
        return num;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public int getNumItems() {
        return items.size();
    }
    public String[][]obtenerItems(){
        
        String[][] misItems = new String[items.size()+ 4 ][3];
        for (int i = 0; i < items.size(); i++){
            
            misItems[i][0] = items.get(i).obtenerCantidad();
            misItems[i][1] = items.get(i).getDescripcion();
            misItems[i][2] = items.get(i).obtenerImporteUnidad();
        }
          return misItems;  
        
    }
    
    public Collection<Item> getItems() {
        return items;
    }
    
    public float calcularImporte() {
        Iterator<Item> i = items.iterator();
        float suma = 0;

        while (i.hasNext()) {
            suma += i.next().importe();
        }
        
        return suma;        
    }
    
    public float calcularIVA() {
        return 0.18f * calcularImporte();
    }
    
    public float calcularTotal() {
        return 1.18f * calcularImporte();
    }
}
