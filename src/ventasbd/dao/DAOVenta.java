/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventasbd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import ventasbd.dao.exception.ErrorBorradoVenta;
import ventasbd.dao.exception.ErrorCreacionVenta;
import ventasbd.modelo.Item;
import ventasbd.modelo.Venta;



public class DAOVenta {
    static DAOVenta instancia = null;
    
    SimpleDateFormat sdf;
    
    private DAOVenta() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    } 
    
    public void crear(Venta v) throws ErrorCreacionVenta {        
        try {
            ConexionBD.instancia().getStatement().execute(
                "insert into ventas values (" +
                Integer.toString(v.getNum()) + ", " +
                "'" + sdf.format(v.getFecha()) + "')"                
                );
                       
            for (Item item: v.getItems()) {
                ConexionBD.instancia().getStatement().execute(
                    "insert into items values (" +
                    Integer.toString(v.getNum()) + ", " +
                    Integer.toString(item.getCantidad()) + ", " +
                    "'" + item.getDescripcion() + "', " +
                    Float.toString(item.getImporteUnidad()) + ")"
                );                
            }
            
        } catch (SQLException e) {
            throw new ErrorCreacionVenta();
        }        
    }
    
    public Venta buscarPorNum(int num) {
        Venta v = null;

        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                "select fecha from ventas where num=" + Integer.toString(num)                
                );
                 
            if (rs.next()) {
                v = new Venta(num);
                v.setFecha(rs.getDate(1));
                
                ResultSet rsi = ConexionBD.instancia().getStatement().executeQuery(
                    "select cantidad, descripcion, importeUnidad from items where num_venta=" + 
                        Integer.toString(num)
                    );
            
                while (rsi.next()) {
                    v.anadirItem(new Item(rsi.getInt(1), rsi.getString(2), rsi.getFloat(3)));
                }
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return v;        
    }

    public void actualizar(Venta v) {
        // Implementar
    }
    
    public void borrar(Venta v) throws ErrorBorradoVenta {
        try {
            ConexionBD.instancia().getStatement().execute(
                    "delete from items where num_venta=" + 
                    Integer.toString(v.getNum())
                    );
            ConexionBD.instancia().getStatement().execute(
                    "delete from ventas where num=" + 
                    Integer.toString(v.getNum())
                    );
        } catch (SQLException e) {
            throw new ErrorBorradoVenta();
        }
    }
    
    public static DAOVenta instancia() {
        if (instancia == null) {
            instancia = new DAOVenta();
        }
        
        return instancia;
    }
}