/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.academico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lemus.carlos.utilidades.Mensaje;
import media.tierra.generico.Persona;

/**
 *
 * @author MekakuZero
 */
public class Alumno extends Persona{
    private String carnet;
    private List<Materia> materias;

    public Alumno(String carnet, String nombre, Date fecha) {
        this.materias = new ArrayList<>();
        this.carnet = carnet;
        super.nombre = nombre;
        super.fechaDeNacimiento = fecha;
    }
    
    public String getCarnet() {
        return carnet;
    }
    
    public static Date ObtenerFechaNacimiento(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "";
        do {
            fecha = Mensaje.ObtenerTexto("Ingrese la fecha de cumpleaños", "", true, Mensaje.FECHA, "Debe digitar bien la fecha YYYY-MM-DD");
            try{
                Date nacimiento = sdf.parse(fecha);
                return nacimiento;
            } catch(Exception e){
                Mensaje.Errores("Error inesperado");
            }
            
        } while (true);
    }
    
    
    
    public boolean PoseeMaterias(){
        return this.materias.size() > 0;
    }
    
    public boolean NombreMateriaUnico(String nombre){
        for(Materia mat : this.materias)
            if(mat.getNombre().equals(nombre)){
                return false;
            }
        
        return true;
    }
    
    public void AddMateria(Materia materia){
        this.materias.add(materia);
    }
    public double ObtenerCum(){ return 0; }
    public void MostrarAprobadas() {}
}
