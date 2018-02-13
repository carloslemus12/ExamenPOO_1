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
        int longi = 0;
        for(Materia materia : this.materias){
                if (!materia.Aprobado) continue;
                longi +=1;
            }
        int[] UV = new int[longi];
        double[] notas = new double[longi];
        
        if (this.materias.size() > 0){
            double CUM;
            String cumref;
            double sumaCUM = 0;
            int sumaUV = 0;
            int longitud = 0;
            int i = 0;

            for(Materia materia : this.materias){
                if (!materia.Aprobado) continue;
                i++;
                UV[i] = materia.getUv();
                notas[i] = materia.getNota();
                longitud += i;
            }
            for (int j = 0; j < longitud; j++) {
                sumaCUM += (UV[j] * notas[j]);
                sumaUV += UV[j];
            }
            CUM = (sumaCUM)/(sumaUV);
            if ((CUM > 0) && (CUM <= 10)) {
                cumref = String.valueOf(CUM);
                Mensaje.Informativo("El CUM es: ",cumref);
            }else{
                 Mensaje.Errores("El CUM es:", "No hay materias aprobadas");
            }
        }else{
            Mensaje.Errores("El CUM es:", "No hay materias registrados");
        }
    }
    public void MostrarAprobadas() {
        if (this.materias.size() > 0) {
            String r = "";
            boolean uso = false;
            
            for(Materia materia : this.materias){
                if (!materia.Aprobado) continue;
                
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
        String info = "Codigo: " + this.carnet + "\nNombre" + this.nombre + "\nFecha de nacimiento: " + super.fechaDeNacimiento.toString() + "\nNumero de materias: " + this.materias.size();
        return info;
    }
}
