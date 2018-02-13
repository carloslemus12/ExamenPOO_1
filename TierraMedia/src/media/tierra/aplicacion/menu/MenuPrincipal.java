/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.aplicacion.menu;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import lemus.carlos.utilidades.Mensaje;
import lemus.carlos.utilidades.Menu;
import media.tierra.Principal;
import media.tierra.academico.Alumno;

/**
 *
 * @author MekakuZero
 */
public class MenuPrincipal extends Menu{
    
    private final Principal principal;
    
    public MenuPrincipal() {
        super("Menu principal");
        
        principal = new Principal();
        
        super.AddOpcion("Añadir alumno", () -> {
            // Añadimos la opccion
            do {
                
                // Campos
                String carnet, nombre;
                Date fecha;
                
                // Obtenemos el carnet
                carnet = principal.ObtenerCarnet();
                
                // Obtenemos el nombre
                nombre = Mensaje.ObtenerTexto("Ingrese el nombre del estudiante", "", true);
                
                // Obtenemos la fehca
                fecha = Alumno.ObtenerFechaNacimiento();
                
                principal.AddAlumno(new Alumno(carnet, nombre, fecha));
                
                Mensaje.Informativo(titulo, "Alumno ingresado con exito");
                
                if (Mensaje.Pregunta(titulo, "¿Desea continuar ingresando alumnos?") == JOptionPane.NO_OPTION)
                    break;
            } while (true);
        });
        
        super.AddOpcion("Seleccionar alumno", () -> {
            // Añadimos la opccion
            if (principal.HayAlumnos()) {
                String carnet = Mensaje.ObtenerTexto("Carnet del estudiante", "", true, Mensaje.CARNET, "El carnet debe de tener XX000000");

                if (principal.ExisteCarnetEstudiantil(carnet)) {

                    Alumno alumno = principal.ObtenerAlumno(carnet);
                    
                } else
                    Mensaje.Errores(titulo, "El carnet no existe");
            } else
                Mensaje.Errores(titulo, "No hay alumnos registrados");
        });
        
        super.AddOpcion("Mostrar alumnos", () -> {
            // Añadimos la opccion
            principal.MostrarAlumno();
        });
        
        super.AddOpcion("Añadir datos por defecto", () -> {
            
        });
    }   
}
