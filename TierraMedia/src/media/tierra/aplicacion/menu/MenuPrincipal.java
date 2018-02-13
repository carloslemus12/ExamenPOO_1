/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.aplicacion.menu;

import lemus.carlos.utilidades.Mensaje;
import lemus.carlos.utilidades.Menu;
import media.tierra.Principal;

/**
 *
 * @author MekakuZero
 */
public class MenuPrincipal extends Menu{
    
    private final Principal principal;
    
    public MenuPrincipal() {
        super("Menu principal");
        
        principal = new Principal();
        
        super.AddOpcion("Alumnos", () -> {
            // Añadimos la opccion
            Mensaje.Informativo("Hola", "Hola");
        });
        
        super.AddOpcion("Materias", () -> {
            // Añadimos la opccion
        });
    }   
}
