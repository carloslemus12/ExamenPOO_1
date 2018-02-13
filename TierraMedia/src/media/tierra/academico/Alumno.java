/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.academico;

import java.util.List;
import media.tierra.generico.Persona;

/**
 *
 * @author MekakuZero
 */
public class Alumno extends Persona{
    String carnet;
    List<Materia> materias;
    
    public void AddMateria(Materia materia){}
    public double ObtenerCum(){ return 0; }
    public void MostrarAprobadas() {}
}
