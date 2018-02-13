/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.aplicacion.menu;

import lemus.carlos.utilidades.Mensaje;
import lemus.carlos.utilidades.Menu;
import media.tierra.academico.Alumno;

/**
 *
 * @author MekakuZero
 */
public class MenuAlumno extends Menu {
    
    private Alumno alumno;
    
    public MenuAlumno(Alumno alumno) {
        super("Gestion del alumno: " + alumno.getNombre());
        this.alumno = alumno;
    
        super.AddOpcion("Mostrar detalle", () -> {
            Mensaje.Informativo(titulo, this.alumno.toString());
        });
        
        super.AddOpcion("Modificar", () -> {
            
        });
        
        super.AddOpcion("Eliminar", () -> {
            
        });
        
        super.AddOpcion("Ver CUM", () -> {
            
        });
        
        super.AddOpcion("Ver materias aprovadas", () -> {
            
        });
        
        super.AddOpcion("Ver todas las materias", () -> {
            
        });
        
        super.AddOpcion("Ingresar materia", () -> {
            
        });
    }
    
}
