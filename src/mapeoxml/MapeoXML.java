/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeoxml;

import com.mapeoxml.model.ModelProveedor;
import com.mapeoxml.model.persistencia.Proveedor;
import java.util.List;

/**
 *
 * @author USER
 */
public class MapeoXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Proveedor proveedor = new Proveedor();
        proveedor.setDescripcion("Hola Mundo");
        /*//ModelProveedor.crearProveedor(proveedor);
        proveedor.setDescripcion("Nuevo");
        ModelProveedor.modificarProveedor(8);
        Proveedor p = ModelProveedor.verProveedor(8);
        List<Proveedor> l=ModelProveedor.listarProveedores();*/
       
        
        
    }
    
}
