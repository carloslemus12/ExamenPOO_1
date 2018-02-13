/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.utilidad;

import lemus.carlos.mysql.Modelo;

/**
 *
 * @author MekakuZero
 */
public class MenuRelacional extends Menu{
    
    protected Modelo relacion;
    
    public MenuRelacional(Modelo relacion, String titulo){
        super(titulo, 4);
        
        this.relacion = relacion;
    
        super.opciones[0] = "Añadir relacion " + relacion.getTabla();
        super.opciones[1] = "Eliminar relacion " + relacion.getTabla();
        super.opciones[2] = "Mostrar relacion " + relacion.getTabla();
        super.opciones[3] = "Volver";
    }

    @Override
    public void Iniciar() {
        while(true){
             String opcion = Mensaje.Opciones(super.titulo, "Opciones:", opciones, "Debe seleccionar alguna opcion");
        
            if (opcion.equals(opciones[0]))
                Agregar();
            else if (opcion.equals(opciones[1]))
                Eliminar();
            else if (opcion.equals(opciones[2]))
                Mostrar();
            else
                break;
        }
    }
    
    public void Agregar(){}
    
    public void Eliminar(){}
    
    public void Mostrar(){}
}
