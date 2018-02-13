/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.aplicacion.menu;

import lemus.carlos.utilidades.Menu;
import media.tierra.academico.Alumno;

/**
 *
 * @author MekakuZero
 */
public class MenuMateria extends Menu{
    
    private Alumno alumno;
    
    public MenuMateria(String titulo, Alumno alumno) {
        super(titulo);
        this.alumno = alumno;
    }
    
}
