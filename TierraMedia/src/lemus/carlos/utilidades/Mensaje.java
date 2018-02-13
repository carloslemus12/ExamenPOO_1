/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author MekakuZero
 */
public class Mensaje {
    // Validaciones
    public static final String TELEFONO = "^[2|7][0-9]{3}-[0-9]{4}$";
    
    // Mensaje para mostrar informacion ////////////////////////////////
    public static void Informativo(String titulo, String cuerpo){
        JOptionPane.showMessageDialog(null, cuerpo, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void Informativo(String cuerpo){
        JOptionPane.showMessageDialog(null, cuerpo, "Informacion!", JOptionPane.INFORMATION_MESSAGE);
    }
    // /////////////////////////////////////////////////////////////////
    
    // Mensaje para mostar errores /////////////////////////////////////
    public static void Errores(String titulo, String cuerpo){
        JOptionPane.showMessageDialog(null, "Error: "+cuerpo, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void Errores(String cuerpo){
        JOptionPane.showMessageDialog(null, "Error: "+cuerpo, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    // ///////////////////////////////////////////////////////////////////
    
    // Menu de opciones //////////////////////////////////////////////////
    public static String Opciones(String titulo, String cuerpo, String[] opciones, String error){
        
        String resultado = "";
        
        while(true){
            resultado = (String) JOptionPane.showInputDialog(null, cuerpo, titulo, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
            if(resultado == null || resultado.isEmpty())
                Errores(error);
            else
                break;
        }
        
        return resultado;
    }
    
    public static String Opciones(String titulo, String cuerpo, Object[] opciones, String error){
        
        String resultado = "";
        
        while(true){
            resultado = (String) JOptionPane.showInputDialog(null, cuerpo, titulo, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
            if(resultado == null || resultado.isEmpty())
                Errores(error);
            else
                break;
        }
        
        return resultado;
    }
    // ///////////////////////////////////////////////////////////////////
    
    // Pregunta SI NO ////////////////////////////////////////////////////
    
    public static int Pregunta(String cuerpo){
        int r = JOptionPane.showConfirmDialog(null, cuerpo, "Pregunta", JOptionPane.YES_NO_OPTION);
        return r;
    }
    
    // ///////////////////////////////////////////////////////////////////
    
    // Obtener datos /////////////////////////////////////////////////////
    public static String Texto(String cuerpo, String error){
        String resultado = "";
        
        while(true){
            resultado = (String) JOptionPane.showInputDialog(null, cuerpo);
            
            if(resultado == null || resultado.isEmpty())
                Errores(error);
            else
                break;
        }
        
        return resultado;
    }
    
    public static String Texto(String cuerpo, String error, String patron, String errorPatron){
        String resultado = "";
        
        Pattern p = Pattern.compile(patron);
        
        
        while(true){
            resultado = (String) JOptionPane.showInputDialog(null, cuerpo);
            
            if(resultado == null || resultado.isEmpty())
                Errores(error);
            else{
                Matcher m = p.matcher(resultado);
                
                if (!m.matches())
                    Errores(errorPatron);
                else
                    break;
            }
        }
        
        return resultado;
    }
    
    public static String Texto(String cuerpo, String defecto, String error){
        String resultado = "";
        
        while(true){
            resultado = (String) JOptionPane.showInputDialog(null, cuerpo, defecto);
            
            if(resultado == null || resultado.isEmpty())
                Errores(error);
            else
                break;
        }
        
        return resultado;
    }
    
    public static String Texto(String cuerpo, String defecto, String error, String patron, String errorPatron){
        String resultado = "";
        
        Pattern p = Pattern.compile(patron);
        
        
        while(true){
            resultado = (String) JOptionPane.showInputDialog(null, cuerpo, defecto);
            
            if(resultado == null || resultado.isEmpty())
                Errores(error);
            else{
                Matcher m = p.matcher(resultado);
                
                if (!m.matches())
                    Errores(errorPatron);
                else
                    break;
            }
        }
        
        return resultado;
    }
    
    public static int Numero(String cuerpo, String error, int min, String rangoError){
        int resultado = -1;
        
        while(true){
            String r = (String) JOptionPane.showInputDialog(null, cuerpo);
            
            try{
                resultado = Integer.parseInt(r);
                
                if (resultado > min)
                    break;
                else
                    Errores(rangoError);
                
            } catch(Exception ex) {
                Errores(error);
            }
        }
        
        return resultado;
    }
    // ///////////////////////////////////////////////////////////////////
}