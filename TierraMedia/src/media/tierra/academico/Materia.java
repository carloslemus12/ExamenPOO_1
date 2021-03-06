/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.academico;

import lemus.carlos.utilidades.Mensaje;

/**
 *
 * @author MekakuZero
 */
public class Materia {
    private String nombre;
    private double nota;
    private int uv;
    private boolean Aprobado;
    private int matricula;

    public Materia(String nombre, double nota, int uv, int matricula) {
        this.nombre = nombre;
        this.nota = nota;
        this.uv = uv;
        this.matricula = matricula;
        
        this.Aprobado = nota >= 6.0;
    }

    public Materia(String nombre, double nota, int uv) {
        this.nombre = nombre;
        this.nota = nota;
        this.uv = uv;
        this.matricula = 1;
        
        this.Aprobado = nota >= 6.0;
    }
    
    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }

    public int getUv() {
        return uv;
    }

    public boolean isAprobado() {
        return Aprobado;
    }
    
    public static int obtenerUvs(){
        String uvs = "";
        
        do {
            try{
                uvs = Mensaje.ObtenerTexto("Unidades valorativas:", "0", true, Mensaje.UVS, "Solo se admiten 1, 2, 3 o 4");
        
                int u = Integer.parseInt(uvs);
            
                return u; 
            } catch(Exception ex){ Mensaje.Errores("Error inesperado"); }
        } while (true);
    }
    
    public static double obtenerNota(){
        do {
            try{
                String nota = Mensaje.ObtenerTexto("Ingrese la nota de la materia:", "1", true, Mensaje.NOTA, "La nota debe de ser numerica");
        
                double u = Double.parseDouble(nota);
                
                if (u <= 0 || u > 10){
                    Mensaje.Errores("Nota de la materia", "La noda no puede ser menor que 0 ni mayor que 10");
                    continue;
                }
                
                return u; 
            } catch(Exception ex){ Mensaje.Errores("Error inesperado"); }
        } while (true);
    }
    
    public static int obtenerMatricula(){
        String uvs = "";
        
        do {
            try{
                uvs = Mensaje.ObtenerTexto("Matricula", "0", true, Mensaje.UVS, "Solo se admiten 1, 2, 3 o 4");
        
                int u = Integer.parseInt(uvs);
            
                return u; 
            } catch(Exception ex){
                Mensaje.Errores("Error inesperado");
            }
        } while (true);
    }

    public void setNota(double nota) {
        this.nota = nota;
        this.Aprobado = nota >= 6.0;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    public int getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nNota: " + nota + "\nUVS: " + uv + "\nAprobado: " + (Aprobado? "Aprobada" : "Reprobada") + "\nMatricula: " + matricula;
    }
    
    
}
