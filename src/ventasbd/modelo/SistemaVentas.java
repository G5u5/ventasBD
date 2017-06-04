/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventasbd.modelo;

import ventasbd.dao.ConexionBD;
import ventasbd.dao.DAOVenta;
import ventasbd.dao.exception.ErrorBorradoVenta;
import ventasbd.dao.exception.ErrorConexionBD;
import ventasbd.dao.exception.ErrorCreacionVenta;

public class SistemaVentas {
    
    public SistemaVentas() throws ErrorConexionBD {
        ConexionBD.crearConexion();
    }
    
    public void nuevaVenta(Venta v) throws ErrorCreacionVenta {
        DAOVenta.instancia().crear(v);
    }
    
    public void borrarVenta(Venta v) throws ErrorBorradoVenta{
        DAOVenta.instancia().borrar(v);
    }
    
    public Venta venta(int num) {
        return DAOVenta.instancia().buscarPorNum(num);
    }
    
    public void cerrar() {
        ConexionBD.desconectar();
    }
}
