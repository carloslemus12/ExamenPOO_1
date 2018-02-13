/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.aplicacion.menu;

import javax.swing.JOptionPane;
import lemus.carlos.utilidades.Mensaje;
import lemus.carlos.utilidades.Menu;
import media.tierra.academico.Alumno;
import media.tierra.academico.Materia;

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
            alumno.MostrarAprobadas();
        });
        
        super.AddOpcion("Ver todas las materias", () -> {
            alumno.MostrarMaterias();
        });
        
        super.AddOpcion("Modificar materia", () -> {
            
        });
        
        super.AddOpcion("Ingresar materia", () -> {
            do {
                String nombre;
                boolean addMar = false;
                Materia materia = null;

                do {
                    // Valores a comprobar
                    addMar = false; 

                    nombre = Mensaje.ObtenerTexto("Nombre de la materia:", "", true);

                    if (alumno.PoseeMaterias()) {
                        if (!alumno.NombreMateriaUnico(nombre)) {
                            materia = alumno.getMateria(nombre);

                            if (materia.getMatricula() < 4) {
                                if (Mensaje.Pregunta(titulo, "¿Se volvera a matricular?") == JOptionPane.YES_OPTION){
                                    addMar = true;
                                    break;
                                } else
                                    Mensaje.Errores(titulo, "Debe poner un nuevo nombre");
                            } else {
                                Mensaje.Errores(titulo, "La materia ya no se puede recursar");
                            }
                        } else
                            break;
                    } else
                        break;
                } while (true);
                
                double nota = Materia.obtenerNota();

                // Si vamos a añadir una matricula
                if (addMar) {
                    int m = materia.getMatricula() + 1;
                    int uv = materia.getUv();
                    
                    alumno.eliminarMateria(materia);

                    alumno.AddMateria(new Materia(nombre, nota, uv, m));

                    Mensaje.Informativo(titulo, "Materia registrada");
                } else {
                    int uv = Materia.obtenerUvs();
                    
                    if (Mensaje.Pregunta(titulo, "¿Desea especificar el numero de matricula?") == JOptionPane.YES_OPTION) {
                        int m = Materia.obtenerMatricula();

                        alumno.AddMateria(new Materia(nombre, nota, uv, m));
                    } else
                        alumno.AddMateria(new Materia(nombre, nota, uv));

                    Mensaje.Informativo(titulo, "Materia registrada");
                }
                
                if (Mensaje.Pregunta(titulo, "¿Desea seguir ingresando materias?") == JOptionPane.NO_OPTION)
                    break;
            } while (true);
        });
    }
    
}
