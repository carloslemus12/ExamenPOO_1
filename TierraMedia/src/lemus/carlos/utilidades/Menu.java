/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.utilidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author MekakuZero
 */
public abstract class Menu {
    
    protected Map<String, Opcion> opciones;
    
    protected String titulo;
    
    public Menu(String titulo){
        this.opciones = new HashMap<>();
        this.titulo = titulo;
    }
    
    // Metodo principal
    public final void Iniciar(){
        do {
            List<String> elecciones = this.ObtenerOpciones();
        
            if (elecciones.size() > 0) {
                elecciones.add("Atras");
                
                // Obtenemos la eleccion
                String eleccion = Mensaje.Opciones(titulo, "Opciones", elecciones.toArray(), "Debe de seleccionar alguna opcion");
                
                if (eleccion.equals("Atras")) {
                    if (Mensaje.Pregunta(titulo, "¿Desea volver atras?") == JOptionPane.YES_OPTION)
                        break;
                } else
                    this.Eleccion(eleccion);
                
            } else {
                Mensaje.Errores("No hay opciones");
                break;
            }
        } while (true);
        
    };
    
    private void Eleccion(String opcion){
        // En caso que la opcion exista
        if (this.opciones.containsKey(opcion)) {
            Opcion accion = this.opciones.get(opcion);
            // Ejecutamos la accion
            accion.Accion();
        }
    }
    
    private List<String> ObtenerOpciones(){
        List<String> opciones = new ArrayList<>();
        
        if (this.opciones.size() > 0)
            for(Map.Entry<String, Opcion> item : this.opciones.entrySet())
                opciones.add(item.getKey());
        
        return opciones;
    }
    
    protected void AddOpcion(String opcion, Opcion accion){
        if (!this.opciones.containsKey(opcion) && !opcion.equals("Atras"))
            this.opciones.put(opcion, accion);
    }
    
    public static interface Opcion {
        public void Accion();
    }
}
