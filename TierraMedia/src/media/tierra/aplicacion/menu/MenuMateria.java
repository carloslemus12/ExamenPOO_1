/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.aplicacion.menu;

import lemus.carlos.utilidades.Mensaje;
import lemus.carlos.utilidades.Menu;
import media.tierra.academico.Alumno;
import media.tierra.academico.Materia;

/**
 *
 * @author MekakuZero
 */
public class MenuMateria extends Menu{
    
    private Materia materia;
    private Alumno alumno;
    
    public MenuMateria(Alumno alumno, Materia materia) {
        super("Opciones de " + materia.getNombre());
        this.materia = materia;
        this.alumno = alumno;
        
        super.AddOpcion("Cambiar uvs", new Opcion() {
            @Override
            public void Accion() {
                int uv = Materia.obtenerUvs();
                materia.setUv(uv);
                Mensaje.Informativo(titulo, "Materia modificada");
            }
            
        });
        
        super.AddOpcion("Cambiar nota", new Opcion(){
            @Override
            public void Accion() {
                double nota = Materia.obtenerNota();
                materia.setNota(nota);
                Mensaje.Informativo(titulo, "Materia modificada");
            }
            
        });
        
        super.AddOpcion("Cambiar matricula",new Opcion(){
            @Override
            public void Accion(){
                int matricula = Materia.obtenerMatricula();
                materia.setMatricula(matricula);
                Mensaje.Informativo(titulo, "Materia modificada");
            }
        });
        
        super.AddOpcion("Eliminar", new Opcion(){
            @Override
            public void Accion(){
                alumno.eliminarMateria(materia);
                Mensaje.Informativo(titulo, "Materia eliminada");
            }
        });
        
        super.AddOpcion("Detalles", new Opcion(){
            @Override
            public void Accion(){
                Mensaje.Informativo(titulo, materia.toString());
            }
        });
    }
    
}
