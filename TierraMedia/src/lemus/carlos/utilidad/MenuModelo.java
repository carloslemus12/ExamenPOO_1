/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.utilidad;

import lemus.carlos.mysql.LectorDatos;
import lemus.carlos.mysql.Modelo;

/**
 *
 * @author MekakuZero
 */
public class MenuModelo extends Menu{
    protected Modelo model;

    protected String[] opcionesSecundarios;
    
    public MenuModelo(Modelo model, String titulo) {
        super(titulo, 4);
        
        this.model = model;
        
        super.opciones[0] = "Insertar " + model.getTabla();
        super.opciones[1] = "Buscar " + model.getTabla();
        super.opciones[2] = "Mostrar todos los " + model.getTabla();
        super.opciones[3] = "Volver";
    
        this.opcionesSecundarios = new String[3];
        this.opcionesSecundarios[0] = "Eliminar " + model.getTabla();
        this.opcionesSecundarios[1] = "Modificar " + model.getTabla();
        this.opcionesSecundarios[2] = "Volver";
    }
    
    public final void Iniciar(){
        while(true){
            String opcion = Mensaje.Opciones(super.titulo, "Opciones:", opciones, "Debe seleccionar alguna opcion");
        
            if (opcion.equals(opciones[0]))
                Guardar();
            else if (opcion.equals(opciones[1]))
                Buscar();
            else if (opcion.equals(opciones[2]))
                VerTodos();
            else
                break;
        }
    }
    
    public final void InicioSecundario(Modelo modelo){
        while(true){
            String opcion = Mensaje.Opciones(super.titulo, modelo.toString() + "\nOpciones:", opcionesSecundarios, "Debe seleccionar alguna opcion");
            
            if (opcion.equals(opcionesSecundarios[0]))
                Eliminar(modelo);
            else if (opcion.equals(opcionesSecundarios[1]))
                Modificar(modelo);
            
            break;
        }
    }
    
    public void Eliminar(Modelo modelo){}
    
    public void Modificar(Modelo modelo){}
    
    public void Guardar(){}
    
    public void Buscar(){}
    
    public void VerTodos(){}
}
