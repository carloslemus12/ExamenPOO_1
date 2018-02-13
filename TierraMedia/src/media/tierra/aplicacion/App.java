/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.aplicacion;

import lemus.carlos.utilidades.Mensaje;
import media.tierra.aplicacion.menu.MenuPrincipal;

/**
 *
 * @author MekakuZero
 */
public class App {
    public static void main(String[] args){
        Mensaje.Informativo("Tierra Media", "Bienvenido al sistema de Tierra Media");
        
        // Aqui se incluiria el menu de opciones
        new MenuPrincipal().Iniciar();
        
        Mensaje.Informativo("Tierra Mieda", "Gracias por usar el sistema de Tierra Media");
    }
}
