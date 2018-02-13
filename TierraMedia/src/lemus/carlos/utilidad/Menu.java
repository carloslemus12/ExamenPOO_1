/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.utilidad;

/**
 *
 * @author MekakuZero
 */
public class Menu {
    protected String titulo;
    protected String[] opciones;
    
    public Menu(String titulo, int opciones) {
        // Titulo del menu
        this.titulo = titulo;
        
        this.opciones = new String[opciones];
    }
    
    public void Iniciar(){
        
    }
}
