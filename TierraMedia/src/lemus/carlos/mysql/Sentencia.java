/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javafx.util.Pair;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MekakuZero
 */
public class Sentencia implements Filtro{

    private Map<String, Pair<String, Object>> campos;
    
    private String tabla;
    
    private String where;
    
    private boolean usoWhere;
    
    private Connection conexion;
    
    public Sentencia(Connection conexion, Map<String, Pair<String, Object>> campos, String tabla) {
        this.campos = campos;
        this.tabla = tabla;
        this.where = "";
        this.usoWhere = false;
        this.conexion = conexion;
    }
    
    @Override
    public Sentencia Where(String campo, Object valor) {
        if (this.campos.containsKey(campo) && valor != null) {
            // Si no hemos usado el campo lo mostramos
            Pair<String, Object> info = campos.get(campo);
            
            if (usoWhere) 
                where += " AND ";
                
            where += campo + " = " + Modelo.TipoDatos.EstructurarCampo(info.getKey(), valor);
            
            usoWhere = true;
        }
        
        return this;
    }

    @Override
    public Sentencia Where(String campo, String operacion, Object valor) {
        if (this.campos.containsKey(campo) && valor != null && (operacion != null || !operacion.isEmpty())) {
            
            Pair<String, Object> info = campos.get(campo);
            
            if (usoWhere) 
                where += " AND ";
                
            where += campo + " " + operacion + " " + Modelo.TipoDatos.EstructurarCampo(info.getKey(), valor);
            
            usoWhere = true;
        }
        
        return this;
    }

    @Override
    public Sentencia WhereID(int id) {
        if (usoWhere) 
            where += " AND ";

        where +=  "id = " + id;

        usoWhere = true;
        
        return this;
    }
    
    public boolean Eliminar(){
        String consulta = "DELETE FROM " + tabla;
        
        if (usoWhere)
            consulta += " WHERE " + where;
        
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement();
                sentencia.execute(consulta);
                
                return true;
            } catch (Exception ex){
                return false;
            }
        }
        
        return false;
    } 
    
    public boolean HayDatos() {
        
        String num = "SELECT COUNT(*) FROM " + tabla;
        
        if (usoWhere)
            num += " WHERE " + where;
        
        if (conexion != null) {
            try{
                // Obtenemos el tamaño de las filas
                Statement numS = conexion.createStatement();
                ResultSet numR = numS.executeQuery(num);
                 
                int size = 0;
                
                while(numR.next())
                    size = numR.getInt(1);
                
                return size > 0;
            } catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
        return false;
    }
    
    public LectorDatos LeerDatos(){
        
        String consulta = "SELECT id";
        
        String num = "SELECT COUNT(*) FROM " + tabla; 
        
        List<String> columnas = new ArrayList<>();
        columnas.add("id");
        
        for(Map.Entry<String, Pair<String, Object>> valor : campos.entrySet()){
            consulta += "," + valor.getKey();
            columnas.add(valor.getKey());
        }
        
        if (usoWhere){
            consulta += " FROM " + tabla + " WHERE " + where;
            num += " WHERE " + where;
        }
        
        if (conexion != null) {
            try{
                // Obtenemos el tamaño de las filas
                Statement numS = conexion.createStatement();
                ResultSet numR = numS.executeQuery(num);
                 
                int size = 0;
                
                while(numR.next())
                    size = numR.getInt(1);
                
                if (size > 0) {
                    // Obtenemos los campos
                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery(consulta);
                
                    List<LectorDatos.Datos> datos = new ArrayList<>();
                    
                    while(resultado.next()){
                        List<Pair<String, Object>> list = new ArrayList<>();
                        
                        for (int i = 0; i < columnas.size(); i++)
                            list.add(new Pair<>(columnas.get(i), resultado.getObject(columnas.get(i))));
                        
                        datos.add(new LectorDatos.Datos(list));
                    }
                    
                    return new LectorDatos(datos);
                } else
                    System.out.println("Error: no hay filas");
                
            } catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
        return new LectorDatos(new ArrayList<>());
    }
}
