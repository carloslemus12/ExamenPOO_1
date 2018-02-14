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
    private String cumbm;
    public Alumno(String carnet, String nombre, Date fecha) {
        this.materias = new ArrayList<>();
        this.carnet = carnet;
        super.nombre = nombre;
        super.fechaDeNacimiento = fecha;
    }
    
    public String getCarnet() {
        return carnet;
    }
    
    public static Date ObtenerFechaNacimiento(String defecto){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "";
        do {
            fecha = Mensaje.ObtenerTexto("Ingrese la fecha de cumpleaños", defecto, true, Mensaje.FECHA, "Debe digitar bien la fecha YYYY-MM-DD");
            try{
                Date nacimiento = sdf.parse(fecha);
                return nacimiento;
            } catch(Exception e){
                Mensaje.Errores("Error inesperado");
            }
            
        } while (true);
    }
    
    public void generarMaterias(){
        this.materias.add(new Materia("LEM", 8, 3));
        this.materias.add(new Materia("POO", 9, 4));
        this.materias.add(new Materia("FisMod2", 5.7, 4));
        this.materias.add(new Materia("FisMod", 7.1, 4));
        this.materias.add(new Materia("PCC", 7.7, 3));
        this.materias.add(new Materia("Mate3", 9.5, 4));
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
    
    public Materia getMateria(String nombre){
        for(Materia mat : this.materias)
            if(mat.getNombre().equals(nombre))
                return mat;
        
        return null;
    }
    
    public void eliminarMateria(Materia materia){
        if (this.materias.contains(materia)) {
            this.materias.remove(materia);
        }
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
    public void MostrarMaterias(){
        if (this.materias.size() > 0) {
            String r = "";
            boolean uso = false;
            
            for(Materia materia : this.materias){
                if(uso) r += "\n";
                
                r += "Nombre: " + materia.getNombre()+ " Nota: " + materia.getNota();
                
                uso = true;
            }
            
            Mensaje.Informativo("Informacion de las materias", r);
                
        } else
            Mensaje.Errores("Informacion de las materias:", "No hay materias registrados");
    }
    

    public void ObtenerCum(){
        if (this.materias.size() <= 0){
            Mensaje.Errores("Gestion estudiantil", "No hay materias registradas");
            return;
        }
        
        List<Materia> materiasAprovadas = new ArrayList<>();
        
        for(Materia materia : this.materias)
            if(materia.isAprobado())
                materiasAprovadas.add(materia);
        
        if (materiasAprovadas.size() <= 0){
            Mensaje.Errores("Gestion estudiantil", "No hay materias aprivadas");
            return;
        }
        
        double merito = 0, valorativa = 0;
        
        for(Materia materia : materiasAprovadas){
            merito += (materia.getNota() * materia.getUv());
            valorativa += materia.getUv();
        }
            
        double cum = merito / valorativa;
        
        Mensaje.Informativo("Gestion estudiantil", "CUM: " + cum);
    }
    public void MostrarAprobadas() {
        if (this.materias.size() > 0) {
            String r = "";
            boolean uso = false;
            
            for(Materia materia : this.materias){
                if (!materia.isAprobado()) continue;
                
                if(uso) r += "\n";
                
                r += "Nombre: " + materia.getNombre()+ " Nota: " + materia.getNota();
                
                uso = true;
            }
            if (uso)
                Mensaje.Informativo("Informacion de las materias", r);
            else
                Mensaje.Errores("Informacion de las materias:", "No hay materias aprobadas");
        } else
            Mensaje.Errores("Informacion de las materias:", "No hay materias registrados");
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        
        String info = "Codigo: " + this.carnet + "\nNombre" + this.nombre + "\nFecha de nacimiento: " + format.format(super.fechaDeNacimiento) + "\nNumero de materias: " + this.materias.size();
        return info;
    }
}
