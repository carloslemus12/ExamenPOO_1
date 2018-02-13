/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.mysql;

import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author MekakuZero
 */
public class LectorDatos {
    
    private List<Datos> datos;
    
    int indice;
    
    public static class Datos{
        private List<Pair<String, Object>> lista;

        public Datos(List<Pair<String, Object>> lista) {
            this.lista = lista;
        }
        
        public Object GetObject(String campo){
            for(Pair<String, Object> valores : lista)
                if (valores.getKey().equals(campo))
                    return valores.getValue();
        
            return null;
        }
    }

    public LectorDatos(List<Datos> datos) {
        this.datos = datos;
        indice = 0;
    }
    
    public boolean Siguiente(){
        boolean r = datos.size() > 0 && indice < datos.size();
        
        if (r)
            indice++;
        
        return r;
    }
    
    public String GetString(String campo){
        Object obj = datos.get(indice-1).GetObject(campo);
        
        if (obj != null)
            return obj.toString();
        else
            return "";
    }
    
    public Object GetObject(String campo){
        Object obj = datos.get(indice-1).GetObject(campo);
        
        return obj;
    }
    
    public Datos GetDatos(){
        return datos.get(indice - 1);
    }
    
    public boolean PoseeDatos(){
        return datos.size() > 0;
    }
    
    public Datos GetPrimerDato(){
        return datos.get(0);
    }
    
    public int GetInt(String campo){
        Object obj = datos.get(indice-1).GetObject(campo);
        
        if (obj != null)
            try{
                int val;
                
                val = Integer.parseInt(obj.toString());
                
                return val;
                
            } catch(Exception ex){
                return -1;
            }
        else
            return -1;
    }
}
