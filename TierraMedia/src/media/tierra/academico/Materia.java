/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.tierra.academico;

/**
 *
 * @author MekakuZero
 */
public class Materia {
    private String nombre;
    private double nota;
    private int uv;
    boolean Aprobado;
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
    
}
