/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra;

import java.util.ArrayList;
import java.util.List;
import lemus.carlos.utilidades.Mensaje;
import media.tierra.academico.Alumno;

/**
 *
 * @author MekakuZero
 */
public class Principal {
    private List<Alumno> alumnos;

    public Principal() {
        this.alumnos = new ArrayList<>();
    }
    
    public String ObtenerCarnet(){
        // Pedimos el nombre del carnet del alumno
        String carnet = "";
        
        do {
            carnet = Mensaje.ObtenerTexto("Carnet del estudiante", "", true, Mensaje.CARNET, "El carnet debe de tener XX000000");
            if (!ExisteCarnetEstudiantil(carnet))
                break;
            else
                Mensaje.Errores("El carnet ya esta registrado");
        } while (true);
        
        return carnet;
    }
    
    public boolean ExisteCarnetEstudiantil(String carnet){
        for(Alumno alumno: this.alumnos){
            if (alumno.getCarnet().equals(carnet.toString())) {
                return true;
            }
        }
        
        return false;
    }
    
    public void AddAlumno(Alumno alumno){
        this.alumnos.add(alumno);
    }
    
    public void MostrarAlumno(){
        if (this.alumnos.size() > 0) {
            String r = "";
            boolean uso = false;
            
            for(Alumno alumno : this.alumnos){
                if(uso) r += "\n";
                
                r += "COD: " + alumno.getCarnet() + " Nombre: " + alumno.getNombre();
                
                uso = true;
            }
            
            Mensaje.Informativo("Informacion de los alumnos", r);
                
        } else
            Mensaje.Errores("Informacion de los alumnos:", "No hay alumnos registrados");
    }
}
